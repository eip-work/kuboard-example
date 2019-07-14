package work.eip.example.svc.book;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.eip.example.svc.base.BaseDAO;

import java.util.List;

/**
 * @Author: shaohuanqing
 */
@Repository
public interface BookDao extends BaseDAO<BookEntity> {

    List<BookEntity> query();

}
