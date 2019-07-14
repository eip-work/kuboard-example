package work.eip.example.svc.base;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDAO<Entity> {

    public long insert(Entity pdLegalEntity);

    public long deleteById(long id);

    public Entity findById(long id);

    public long update(Entity pdLegalEntity);

}
