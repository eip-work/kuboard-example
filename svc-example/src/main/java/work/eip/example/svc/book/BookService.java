package work.eip.example.svc.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.eip.example.svc.base.AssertUtils;
import work.eip.example.svc.base.BaseService;

import java.util.List;

/**
 * @Author: shaohuanqing
 * @Date: 2019/7/12
 */
@Service
@Slf4j
public class BookService extends BaseService<BookCreateDTO, BookDTO, BookEntity, BookDao> {

    @Autowired
    private BookDao bookDao;

    @Override
    protected BookEntity instanciateEntity() {
        return new BookEntity();
    }

    @Override
    protected BookDTO instanciateDTO() {
        return new BookDTO();
    }

    @Transactional
    public long create(BookCreateDTO createDTO) {
        BookEntity entity = this.instanciateEntity();
        BeanUtils.copyProperties(createDTO, entity);
        long id = this.snowFlakeService.nextId();
        entity.setId(id);
        log.debug("插入记录: " + entity);
        bookDao.insert(entity);
        return id;
    }

    public List<BookDTO> query() {
        List<BookEntity> entityList = bookDao.query();
        log.debug("查询结果: " + entityList.size());
        return convertToDTOList(entityList);
    }

    public BookDTO show(Long id) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        BookDTO dto = this.instanciateDTO();
        BookEntity entity = this.dao.findById(id);
        log.debug("查询结果: " + entity);
        AssertUtils.assertNotNull(entity, "主键未找到: " + id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }


    @Transactional
    public void modify(Long id, BookCreateDTO bookCreateDTO) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        AssertUtils.assertNotNull(bookCreateDTO, "被修改的对象不能为空");
        BookEntity entity = this.instanciateEntity();
        entity.setId(id);
        BeanUtils.copyProperties(bookCreateDTO, entity);
        dao.update(entity);
    }


    public void delete(Long id) {
        AssertUtils.assertTrue(id != null, "id 不能为空");
        long modified = dao.deleteById(id);
        AssertUtils.assertTrue(modified == 1, "主键未找到: " + id);
    }


}
