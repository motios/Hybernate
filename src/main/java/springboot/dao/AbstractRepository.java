package springboot.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * Created by We on 21.06.2016.
 */
public abstract class AbstractRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
