package by.isethesal.app.service.impl;

import by.isethesal.app.model.dao.DepartmentDao;
import by.isethesal.app.model.dao.EmployeeDao;
import by.isethesal.app.model.entity.Department;
import by.isethesal.app.model.entity.Employee;
import by.isethesal.app.service.EmployeeService;
import by.isethesal.app.service.error.exception.EntityNotFoundException;
import by.isethesal.app.service.error.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static by.isethesal.app.validation.DepartmentValidator.isNameValid;
import static by.isethesal.app.validation.EmployeeValidator.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @Override
    public Employee findById(long id) {
        return employeeDao.findById(id).orElseThrow(() -> new EntityNotFoundException("id=" + id));
    }

    @Override
    public List<Employee> findAll(int amount, int page) {
        checkPage(amount, page);
        return employeeDao.findAll(amount, page - 1);
    }

    @Override
    public boolean delete(long id) {
        employeeDao.findById(id).orElseThrow(() -> new EntityNotFoundException("id=" + id));
        return employeeDao.delete(id);
    }

    @Override
    @Transactional
    public Employee create(Employee employee) {
        checkEmployeeCreate(employee);
        findEmployeeDepartment(employee);
        return employeeDao.create(employee);
    }

    @Override
    @Transactional
    public Employee update(Employee employee) {
        Long id = employee.getId();
        if (!employeeDao.findById(id).isPresent()) {
            throw new EntityNotFoundException("id=" + id);
        }
        checkEmployeeUpdate(employee);
        findEmployeeDepartment(employee);
        return employeeDao.update(employee);
    }

    private void findEmployeeDepartment(Employee employee) {
        Department department = employee.getDepartment();
        if (department != null) {
            String name = department.getName();
            Optional<Department> optionalDepartment = departmentDao.findByName(name);
            Department refreshed = optionalDepartment.orElseGet(() -> departmentDao.create(department));
            employee.setDepartment(refreshed);
        }
    }

    private void checkEmployeeCreate(Employee employee) {
        StringBuilder errorMessage = new StringBuilder();
        String firstName = employee.getFirstName();
        if (!isFirstNameValid(firstName)) {
            errorMessage.append("Incorrect firstname ").append(firstName).append("\n");
        }
        String lastName = employee.getLastName();
        if (!isLastNameValid(lastName)) {
            errorMessage.append("Incorrect lastname ").append(lastName).append("\n");
        }
        String jobTitle = employee.getJobTitle();
        if (!isJobTitleValid(jobTitle)) {
            errorMessage.append("Incorrect job title ").append(jobTitle).append("\n");
        }
        String gender = employee.getGender();
        if (!isGenderValid(gender)) {
            errorMessage.append("Incorrect birth gender ").append(gender).append("\n");
        }
        LocalDateTime birthDate = employee.getBirthDate();
        if (!isBirthDateValid(birthDate)) {
            errorMessage.append("Incorrect birth date ").append(birthDate);
        }
        Department department = employee.getDepartment();
        if ((department != null)) {
            String name = department.getName();
            if (!isNameValid(name)) {
                errorMessage.append("Incorrect department name ").append(name);
            }
        } else {
            errorMessage.append("Incorrect department ");
        }
        String errorMessageValue = errorMessage.toString();
        if (!errorMessageValue.isEmpty()) {
            throw new ValidationException(errorMessageValue);
        }
    }

    private void checkEmployeeUpdate(Employee employee) {
        StringBuilder errorMessage = new StringBuilder();
        String firstName = employee.getFirstName();
        if ((firstName != null) && !isFirstNameValid(firstName)) {
            errorMessage.append("Incorrect firstname ").append(firstName).append("\n");
        }
        String lastName = employee.getLastName();
        if ((lastName != null) && !isLastNameValid(lastName)) {
            errorMessage.append("Incorrect lastname ").append(lastName).append("\n");
        }
        String jobTitle = employee.getJobTitle();
        if ((jobTitle != null) && !isJobTitleValid(jobTitle)) {
            errorMessage.append("Incorrect job title ").append(jobTitle).append("\n");
        }
        String gender = employee.getGender();
        if ((gender != null) && !isGenderValid(gender)) {
            errorMessage.append("Incorrect birth gender ").append(gender).append("\n");
        }
        LocalDateTime birthDate = employee.getBirthDate();
        if ((birthDate != null) && !isBirthDateValid(birthDate)) {
            errorMessage.append("Incorrect birth date ").append(birthDate).append("\n");
        }
        Department department = employee.getDepartment();
        if ((department != null)) {
            String name = department.getName();
            if ((name != null) && !isNameValid(name)) {
                errorMessage.append("Incorrect department name ").append(name);
            }
        }
        String errorMessageValue = errorMessage.toString();
        if (!errorMessageValue.isEmpty()) {
            throw new ValidationException(errorMessageValue);
        }
    }
}
