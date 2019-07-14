package work.eip.example.svc.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
abstract public class BaseService<CreateDTO, DTO, Entity, DAO extends BaseDAO<Entity>> {

    @Autowired
    protected SnowFlakeService snowFlakeService;

    @Autowired
    protected DAO dao;

    protected abstract Entity instanciateEntity();

    protected abstract DTO instanciateDTO();

    public long create(CreateDTO createDTO, String defaultStatus) {
        Entity entity = instanciateEntity();
        BeanUtils.copyProperties(createDTO, entity);
        long id = snowFlakeService.nextId();
        setId(entity, id);
        setStatus(entity, defaultStatus);
        log.debug("插入记录: " + entity);
        dao.insert(entity);
        return id;
    }

    public void delete(Long id) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        long modified = dao.deleteById(id);
        AssertUtils.assertTrue(modified == 1, "主键未找到: " + id);
    }

    public DTO show(Long id) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        DTO dto = instanciateDTO();
        Entity entity = dao.findById(id);
        log.debug("查询结果: " + entity);
        AssertUtils.assertNotNull(entity, "主键未找到: " + id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    protected List<DTO> convertToDTOList(List<Entity> entities) {
        List<DTO> dtoList = new ArrayList<>();
        entities.forEach(entity -> {
            DTO dto = instanciateDTO();
            BeanUtils.copyProperties(entity, dto);
            dtoList.add(dto);
        });
        return dtoList;
    }

    public void modify(Long id, CreateDTO createDTO) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        AssertUtils.assertNotNull(createDTO, "被修改的对象不能为空");
        Entity entity = instanciateEntity();
        setId(entity, id);
        BeanUtils.copyProperties(createDTO, entity);
        log.debug("修改记录: " + entity);
        long modified = dao.update(entity);
        AssertUtils.assertTrue(modified == 1, "主键未找到: " + id);
    }

    public void modifyStatus(Long id, String status) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        Entity entity = instanciateEntity();
        setId(entity, id);
        setStatus(entity, status);
        log.debug("修改状态: " + entity);
        long modified = dao.update(entity);
        AssertUtils.assertTrue(modified == 1, "主键未找到: " + id);
    }

    private void setId(Entity entity, long id) {
        try {
            Class klass = entity.getClass();
            Method setId = klass.getMethod("setId", long.class);
            setId.invoke(entity, id);
        } catch (NoSuchMethodException e) {
            throw new SvcExecption(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new SvcExecption(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new SvcExecption(e.getMessage());
        }
    }

    private void setStatus(Entity entity, String status) {
        try {
            Class klass = entity.getClass();
            Method setStatus = klass.getMethod("setStatus", String.class);
            setStatus.invoke(entity, status);
        } catch (NoSuchMethodException e) {
            throw new SvcExecption(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new SvcExecption(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new SvcExecption(e.getMessage());
        }
    }
}
