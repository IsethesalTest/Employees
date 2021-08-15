package by.isethesal.app.service.impl;

import by.isethesal.app.model.dao.DepartmentDao;
import by.isethesal.app.model.dao.EmployeeDao;
import by.isethesal.app.model.entity.Department;
import by.isethesal.app.model.entity.Employee;
import by.isethesal.app.service.error.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {
    static Department department;
    static Employee employee;

    EmployeeServiceImpl service;
    EmployeeDao employeeDao;
    DepartmentDao departmentDao;

    @BeforeEach
    public void setUp() {
        department = new Department(1L, "IT");
        employee = new Employee(1L, "Illia", "Aheyeu", department, "Developer", "Male", LocalDateTime.parse("2001-04-12"));
        employeeDao = mock(EmployeeDao.class);
        departmentDao = mock(DepartmentDao.class);
        service = new EmployeeServiceImpl(employeeDao, departmentDao);
    }

    @Test
    void findById() {
        when(employeeDao.findById(1L)).thenReturn(Optional.of(employee));
        Employee actual = service.findById(1L);
        Employee expected = employee;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByIdFail() {
        when(employeeDao.findById(-3L)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(-3L));
    }

    @Test
    void findAll() {
        when(employeeDao.findAll(100, 0)).thenReturn(Collections.singletonList(employee));
        List<Employee> actual = service.findAll(100, 1);
        List<Employee> expected = Collections.singletonList(employee);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete() {
        when(employeeDao.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeDao.delete(1L)).thenReturn(true);
        boolean condition = service.delete(1L);
        Assertions.assertTrue(condition);
    }

    @Test
    void deleteFail() {
        when(employeeDao.findById(99L)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.delete(99L));
    }

    @Test
    void create() {
        when(employeeDao.create(employee)).thenReturn(employee);
        when(departmentDao.findByName(department.getName())).thenReturn(Optional.of(department));
        Employee actual = service.create(employee);
        Employee expected = employee;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() {
        when(employeeDao.findById(1L)).thenReturn(Optional.of(employee));
        when(departmentDao.findByName(department.getName())).thenReturn(Optional.of(department));
        when(employeeDao.update(employee)).thenReturn(employee);
        Employee actual = service.update(employee);
        Employee expected = employee;
        Assertions.assertEquals(expected, actual);
    }
}