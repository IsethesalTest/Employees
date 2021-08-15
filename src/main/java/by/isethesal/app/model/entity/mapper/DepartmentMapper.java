package by.isethesal.app.model.entity.mapper;

import by.isethesal.app.model.entity.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Department mapper
 *
 * @author Illia Aheyeu
 */
public class DepartmentMapper implements RowMapper<Department> {
    private static final String DEPARTMENT_ID = "department_id";
    private static final String DEPARTMENT_NAME = "name";

    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong(DEPARTMENT_ID);
        String name = resultSet.getString(DEPARTMENT_NAME);
        return new Department(id, name);
    }
}
