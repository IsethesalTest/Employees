package by.isethesal.app.service.impl;

import by.isethesal.app.model.dao.DepartmentDao;
import by.isethesal.app.model.entity.Department;
import by.isethesal.app.service.DepartmentService;
import by.isethesal.app.service.error.exception.EntityAlreadyExistsException;
import by.isethesal.app.service.error.exception.EntityNotFoundException;
import by.isethesal.app.service.error.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.isethesal.app.validation.DepartmentValidator.isNameValid;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department findById(long id) {
        return departmentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("id=" + id));
    }

    @Override
    public List<Department> findAll(int amount, int page) {
        return departmentDao.findAll(amount, page - 1);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        return departmentDao.delete(id);
    }

    @Override
    @Transactional
    public Department create(Department department) {
        String name = department.getName();
        checkDepartmentValid(name);
        departmentDao.findByName(name).ifPresent(department1 -> {
            throw new EntityAlreadyExistsException("name=" + name);
        });
        return departmentDao.create(department);
    }

    private void checkDepartmentValid(String name) {
        if (!isNameValid(name)) {
            String errorMessageValue = "Incorrect department name " + name;
            throw new ValidationException(errorMessageValue);
        }
    }

    @Override
    @Transactional
    public Department update(Department department) {
        Long id = department.getId();
        if (!departmentDao.findById(id).isPresent()) {
            throw new EntityNotFoundException("id=" + id);
        }
        return departmentDao.update(department);
    }

    @Override
    public Department findByName(String name) {
        return departmentDao.findByName(name).orElseThrow(() -> new EntityNotFoundException("name=" + name));
    }
}
