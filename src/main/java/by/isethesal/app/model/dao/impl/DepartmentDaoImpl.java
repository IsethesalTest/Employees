package by.isethesal.app.model.dao.impl;

import by.isethesal.app.model.dao.DepartmentDao;
import by.isethesal.app.model.entity.Department;
import by.isethesal.app.model.entity.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static by.isethesal.app.model.dao.impl.SqlQueryHolder.*;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Department> findById(long id) {
        return jdbcTemplate.query(FIND_DEPARTMENT_BY_ID, new DepartmentMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Department> findByName(String name) {
        return jdbcTemplate.query(FIND_DEPARTMENT_BY_NAME, new DepartmentMapper(), name).stream().findFirst();
    }

    @Override
    public List<Department> findAll(int amount, int page) {
        return jdbcTemplate.query(FIND_ALL_DEPARTMENTS, new DepartmentMapper(), amount, page * amount);
    }

    @Override
    public Department create(Department department) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, department.getName());
            return ps;
        }, keyHolder);
        long id = (long) keyHolder.getKeys().get(DEPARTMENT_ID_KEY_HOLDER);
        return findById(id).get();
    }
}
