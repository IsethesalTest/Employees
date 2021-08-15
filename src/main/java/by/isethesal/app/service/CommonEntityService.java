package by.isethesal.app.service;

import by.isethesal.app.model.entity.Entity;
import by.isethesal.app.service.error.exception.IncorrectPageException;

import java.util.List;

/**
 * Common Entity Service interface which allows CRUD operations
 *
 * @param <T> entity which implements {@link Entity}
 * @author Illia Aheyeu
 */
public interface CommonEntityService<T extends Entity> {

    /**
     * Find {@link Entity} by id or throw {@link by.isethesal.app.service.error.exception.EntityNotFoundException EntityNotFoundException}
     *
     * @param id {@link Entity} id
     * @return {@link Entity}
     */
    T findById(long id);

    /**
     * Find all {@link Entity Entities} with specified amount and page or throw {@link by.isethesal.app.service.error.exception.EntityNotFoundException EntityNotFoundException}
     *
     * @param amount amount of {@link Entity Entities} per page
     * @param page   page
     * @return <code>List</code> of {@link Entity Entities}
     */
    List<T> findAll(int amount, int page);

    /**
     * Delete {@link Entity} or throw {@link by.isethesal.app.service.error.exception.EntityNotFoundException EntityNotFoundException}
     *
     * @param id {@link Entity} id
     * @return <code>true</code> if successfully deleted, otherwise <code>false</code>
     */
    boolean delete(long id);

    /**
     * Create {@link Entity} or throw {@link by.isethesal.app.service.error.exception.ValidationException ValidationException}
     *
     * @param object {@link Entity}
     * @return created {@link Entity}
     */
    T create(T object);

    /**
     * Update {@link Entity} or throw {@link by.isethesal.app.service.error.exception.ValidationException ValidationException}
     *
     * @param object {@link Entity}
     * @return created {@link Entity}
     */
    T update(T object);

    /**
     * Default validation for pagination
     *
     * @param amount amount of {@link Entity Entities} per page
     * @param page   page
     */
    default void checkPage(int amount, int page) {
        if (!((page > 0) && (amount >= 0))) {
            throw new IncorrectPageException();
        }
    }
}
