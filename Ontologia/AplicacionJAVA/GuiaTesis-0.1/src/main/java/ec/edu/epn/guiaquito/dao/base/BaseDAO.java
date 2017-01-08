package ec.edu.epn.guiaquito.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * JPA implementation of CRUD methods.
 *
 * @param T  The entity of the service.
 * @param ID Identifier class.
 * @author Xavier Ñauñay <xavierxc14@gmail.com>
 * @version 1.0
 */
public abstract class BaseDAO<T, ID extends String> {

	/**
	 * Select statement.
	 */
	protected static final String SELECT_STATEMENT = "SELECT e FROM ";
	/**
	 * Default serialization id.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Generic class for the service.
	 */
	private final Class<T> entity;
	/**
	 * Name of the class for the service.
	 */
	private final String entityName;
	/**
	 * Entity manager for the persistence unit.
	 */
	@PersistenceContext(unitName = "GuiaQuitoPU")
	private EntityManager entityManager;

	/**
	 * Create a service for the entity using introspection.
	 */
	@SuppressWarnings("unchecked")
	public BaseDAO() {
		this.entity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.entityName = entity.getSimpleName();
	}


	/**
	 * Create a service for the entity.
	 */
	@SuppressWarnings("unchecked")
	public BaseDAO(Class<T> parametrizedEntity) {
		this.entity = parametrizedEntity;
		this.entityName = entity.getSimpleName();
	}


	/**
	 * Get all entities.
	 *
	 * @return List of all entities.
	 * @throws Exception When an error occurs.
	 */
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

	/**
	 * Get an entity by its @Id.
	 *
	 * @param id PK of the entity.
	 * @return T Recovered entity.
	 * @throws Exception When an error occurs.
	 */
	public T findById(final ID id) throws Exception {
		try {
			return this.getEntityManager().find(entity, id);
		} catch (Exception ex) {
			throw new Exception("findById: No se ha podido recuperar la entity '" + getEntityName()
					+ "' con identificador: " + id, ex);
		}
	}

	/**
	 * Persist a new entity in the database.
	 *
	 * @param entity The entity to persist.
	 * @return entity The created with updated values.
	 * @throws Exception When an error occurs.
	 */
	public T create(final T entity) throws Exception {
		getEntityManager().persist(entity);
		return entity;
	}

	/**
	 * Update an entity.
	 *
	 * @param entity The entity to update.
	 * @return entity The updated entity.
	 * @throws Exception When an error occurs.
	 */
	public T update(final T entity) throws Exception {
		return getEntityManager().merge(entity);
	}

	/**
	 * Delete an entity.
	 *
	 * @param entity The entity to delete.
	 * @throws Exception When an error occurs.
	 */
	public void delete(final T entity) throws Exception {
		T deleted = getEntityManager().merge(entity);
		getEntityManager().remove(deleted);
	}

	/**
	 * @return the entityManager.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getEntity() {
		return entity;
	}

	public String getEntityName() {
		return entityName;
	}
}
