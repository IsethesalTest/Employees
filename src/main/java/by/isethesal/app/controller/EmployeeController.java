package by.isethesal.app.controller;

import by.isethesal.app.model.entity.Employee;
import by.isethesal.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee controller.
 * URI: /api/v1/employees
 *
 * @author Illia Aheyeu
 * @see Employee
 */
@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    /**
     * Interface allows manage {@link Employee} object
     */
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Find {@link Employee} by its id
     *
     * @param id {@link Employee} id
     * @return {@link Employee}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    /**
     * Find all {@link Employee Employees} this specified or default pagination
     *
     * @param amount amount {@link Employee} in page
     * @param page page
     * @return <code>List</code> of {@link Employee Departments}
     */
    @GetMapping
    public ResponseEntity<List<Employee>> findAll(@RequestParam(defaultValue = "10") int amount,
                                                  @RequestParam(defaultValue = "1") int page) {
        return new ResponseEntity<>(employeeService.findAll(amount, page), HttpStatus.OK);
    }

    /**
     * Create {@link Employee}
     *
     * @param employee {@link Employee}
     * @return {@link Employee}
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
    }

    /**
     * Update {@link Employee}
     *
     * @param employee {@link Employee}
     * @return updated {@link Employee}
     */
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    /**
     * Delete {@link Employee}
     *
     * @param id {@link Employee} id
     * @return {@link Employee}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }
}
