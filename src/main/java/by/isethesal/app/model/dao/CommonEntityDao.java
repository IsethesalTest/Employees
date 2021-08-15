package by.isethesal.app.model.dao;

import by.isethesal.app.model.entity.Entity;

import java.util.List;
import java.util.Optional;

/**
 * Common Entity Dao interface which allows CRUD operations
 *
 * @param <T> entity which implements {@link Entity}
 * @author Illia Aheyeu
 */
public interface CommonEntityDao<T extends Entity> {

    /**
     * Find {@link Entity} by id
     *
     * @param id {@link Entity} id
     * @return <code>Optional</code> {@link Entity}
     */
    Optional<T> findById(long id);

    /**
     * Find all {@link Entity Entities} with specified amount and page
     *
     * @param amount amount of {@link Entity Entities} per page
     * @param page   page
     * @return <code>Optional</code> {@link Entity}
     */
    List<T> findAll(int amount, int page);

    /**
     * Delete {@link Entity}
     *
     * @param id {@link Entity} id
     * @return <code>true</code> if successfully deleted, otherwise <code>false</code>
     */
    boolean delete(long id);

    /**
     * Create {@link Entity}
     *
     * @param object {@link Entity}
     * @return created {@link Entity}
     */
    T create(T object);

    /**
     * Update {@link Entity}
     *
     * @param object {@link Entity}
     * @return updated {@link Entity}
     */
    T update(T object);
}
