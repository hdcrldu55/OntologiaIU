
package ec.edu.epn.guiaquito.services.base;

import java.io.Serializable;
import java.util.List;


/**
 * Generic Interface with standard CRUD methods.
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> extends Serializable {

    /**
     * Get all entities.
     *
     * @return List of all entities.
     * @throws Exception When an error occurs.
     */
    List<T> findAll() throws Exception;

    /**
     * Get an entity by its @Id.
     *
     * @param id PK of the entity.
     * @return T Recovered entity.
     * @throws Exception When an error occurs.
     */
    T findById(ID id) throws Exception;

    /**
     * Persist a new entity in the database.
     *
     * @param entity The entity to persist.
     * @return entity The created with updated values.
     * @throws Exception When an error occurs.
     */
    T create(T entity) throws Exception;

    /**
     * Update an entity.
     *
     * @param entity The entity to update.
     * @return entity The updated entity.
     * @throws Exception When an error occurs.
     */
    T update(T entity) throws Exception;

    /**
     * Delete an entity.
     *
     * @param entity The entity to delete.
     * @throws Exception When an error occurs.
     */
    void delete(T entity) throws Exception;
}
