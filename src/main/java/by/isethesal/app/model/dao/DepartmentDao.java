package by.isethesal.app.model.dao;

import by.isethesal.app.model.entity.Department;
import by.isethesal.app.model.entity.Entity;

import java.util.Optional;

/**
 *{@link Department} Dao interface
 *
 * @author Illia Aheyeu
 */
public interface DepartmentDao extends CommonEntityDao<Department> {

    /**
     * Find {@link Department} by name
     *
     * @param name {@link Department} name
     * @return <code>Optional</code> {@link Department}
     */
    Optional<Department> findByName(String name);

    /**
     * Forbidden update operation
     *
     * @param department {@link Department}
     * @return {@link Department}
     */
    @Override
    default Department update(Department department) {
        throw new UnsupportedOperationException();
    }

    /**
     * Forbidden delete operation
     *
     * @param id {@link Department} id
     * @return <code>true</code> if successfully updated, otherwise <code>false</code>
     */
    @Override
    default boolean delete(long id) {
        throw new UnsupportedOperationException();
    }
}
