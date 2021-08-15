package by.isethesal.app.model.dao.impl;

import by.isethesal.app.model.dao.EmployeeDao;
import by.isethesal.app.model.entity.Employee;
import by.isethesal.app.model.entity.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static by.isethesal.app.model.dao.impl.SqlQueryHolder.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Employee> findById(long id) {
        return jdbcTemplate.query(FIND_EMPLOYEE_BY_ID, new EmployeeMapper(), id).stream().findFirst();
    }

    @Override
    public List<Employee> findAll(int amount, int page) {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES, new EmployeeMapper(), amount, page * amount);
    }

    @Override
    public boolean delete(long id) {
        return (jdbcTemplate.update(DELETE_FROM_EMPLOYEE_BY_ID, id) > 0);
    }

    @Override
    public Employee create(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            fillEmployeeStatement(employee, ps);
            return ps;
        }, keyHolder);
        long id = (long) keyHolder.getKeys().get(EMPLOYEE_ID_KEY_HOLDER);
        return findById(id).get();
    }


    @Override
    public Employee update(Employee employee) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE);
            fillEmployeeStatement(employee, ps);
            ps.setLong(7, employee.getId());
            return ps;
        });
        return findById(employee.getId()).get();
    }

    private void fillEmployeeStatement(Employee employee, PreparedStatement ps) throws SQLException {
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setLong(3, employee.getDepartment().getId());
        ps.setString(4, employee.getJobTitle());
        ps.setString(5, employee.getGender());
        ps.setString(6, String.valueOf(employee.getBirthDate()));
    }
}
