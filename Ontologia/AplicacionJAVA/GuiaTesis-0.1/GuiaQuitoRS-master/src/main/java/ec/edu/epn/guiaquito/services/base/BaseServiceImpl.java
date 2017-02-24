package ec.edu.epn.guiaquito.services.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * JPA implementation of CRUD methods.
 *
 * @param T  The entity of the service.
 * @param ID Identifier class.
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements
        BaseService<T, ID> {

    /**
     * Default serialization id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Generic class for the service.
     */
    private final Class<T> entity;

    /**
     * Entity manager for the persistence unit.
     */
    @PersistenceContext(unitName = "GuiaQuitoPU")
    private EntityManager entityManager;

    /**
     * Name of the class for the service.
     */
    private final String entityName;

    /**
     * Select statement.
     */
    private static final String SELECT_STATEMENT = "SELECT e FROM ";

    /**
     * Constructor with class and entity manager.
     *
     * @param entity        The entity of the service.
     * @param entityManager Entity manager for the persistence unit.
     */
    protected BaseServiceImpl(final Class<T> entity, final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entity = entity;
        this.entityName = entity.getSimpleName();
    }

    /**
     * Create a service for the entity using introspection.
     */
    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        final Class<T> aType = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.entity = aType;
        this.entityName = entity.getSimpleName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() throws Exception {
        List<T> results;
        try {
            results = this.getEntityManager().createQuery(SELECT_STATEMENT + this.getEntityName() + " e")
                    .getResultList();
            return results;
        } catch (Exception ex) {
            throw new Exception("findAll: No se ha podido recuperar las entidades: " + getEntityName(), ex);
        }

    }


    @Override
    public T findById(final ID id) throws Exception {
        try {
            return this.getEntityManager().find(entity, id);
        } catch (Exception ex) {
            throw new Exception("findById: No se ha podido recuperar la entity '" + getEntityName()
                    + "' con identificador: " + id, ex);
        }
    }

    @Override
    public T create(final T entity) throws Exception {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public T update(final T entity) throws Exception {
        return getEntityManager().merge(entity);
    }

    @Override
    public void delete(final T entity) throws Exception {
        T deleted = getEntityManager().merge(entity);
        getEntityManager().remove(deleted);
    }

    /**
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> getEntity() {
        return entity;
    }

    public String getEntityName() {
        return entityName;
    }
}
