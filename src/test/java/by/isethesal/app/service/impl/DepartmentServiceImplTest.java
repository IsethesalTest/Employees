package by.isethesal.app.service.impl;

import by.isethesal.app.model.dao.DepartmentDao;
import by.isethesal.app.model.entity.Department;
import by.isethesal.app.service.error.exception.EntityAlreadyExistsException;
import by.isethesal.app.service.error.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {
    static Department department;

    DepartmentServiceImpl service;
    DepartmentDao dao;

    @BeforeEach
    public void setUp() {
        department = new Department(1L, "IT");
        dao = mock(DepartmentDao.class);
        service = new DepartmentServiceImpl(dao);
    }

    @Test
    void findById() {
        when(dao.findById(1L)).thenReturn(Optional.of(department));
        Department actual = service.findById(1L);
        Department expected = department;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByIdFail() {
        when(dao.findById(11L)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(11L));
    }

    @Test
    void findAll() {
        when(dao.findAll(100, 0)).thenReturn(Collections.singletonList(department));
        List<Department> actual = service.findAll(100, 1);
        List<Department> expected = Collections.singletonList(department);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete() {
        when(dao.findById(1L)).thenReturn(Optional.of(department));
        when(dao.delete(1L)).thenReturn(true);
        boolean condition = service.delete(1L);
        Assertions.assertTrue(condition);
    }

    @Test
    void create() {
        when(dao.findByName(department.getName())).thenReturn(Optional.empty());
        when(dao.create(department)).thenReturn(department);
        Department actual = service.create(department);
        Department expected = service.create(department);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createFail() {
        when(dao.findByName(department.getName())).thenReturn(Optional.of(department));
        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> service.create(department));
    }

    @Test
    void update() {
        when(dao.findById(department.getId())).thenReturn(Optional.of(department));
        when(dao.update(department)).thenReturn(department);
        Department actual = service.update(department);
        Department expected = department;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateFail() {
        when(dao.findById(department.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.update(department));
    }

    @Test
    void findByName() {
        when(dao.findByName(department.getName())).thenReturn(Optional.of(department));
        Department actual = service.findByName(department.getName());
        Department expected = department;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByNameFail() {
        when(dao.findByName(department.getName())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findByName(department.getName()));
    }
}