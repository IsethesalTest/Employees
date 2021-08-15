package by.isethesal.app.model.entity.mapper;

import by.isethesal.app.model.entity.Department;
import by.isethesal.app.model.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Employee mapper
 *
 * @author Illia Aheyeu
 */
public class EmployeeMapper implements RowMapper<Employee> {
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String DEPARTMENT_ID = "department_id";
    private static final String DEPARTMENT_NAME = "name";
    private static final String JOB_TITLE = "job_title";
    private static final String GENDER = "gender";
    private static final String DATE_OF_BIRTH = "date_of_birth";

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong(EMPLOYEE_ID);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        Long departmentId = resultSet.getLong(DEPARTMENT_ID);
        String departmentName = resultSet.getString(DEPARTMENT_NAME);
        String jobTitle = resultSet.getString(JOB_TITLE);
        String gender = resultSet.getString(GENDER);
        LocalDateTime birthDate = resultSet.getTimestamp(DATE_OF_BIRTH).toLocalDateTime();
        Department department = new Department(departmentId, departmentName);
        return new Employee(id, firstName, lastName, department, jobTitle, gender, birthDate);
    }
}
