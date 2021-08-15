package by.isethesal.app.service;

import by.isethesal.app.model.entity.Department;

/**
 * {@link Department} Service interface
 *
 * @author Illia Aheyeu
 */
public interface DepartmentService extends CommonEntityService<Department> {
    /**
     * Find {@link Department} by name or throw {@link by.isethesal.app.service.error.exception.EntityNotFoundException EntityNotFoundException}
     *
     * @param name {@link Department} name
     * @return {@link Department}
     */
    Department findByName(String name);
}
