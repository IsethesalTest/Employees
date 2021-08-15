package by.isethesal.app.controller;

import by.isethesal.app.model.entity.Department;
import by.isethesal.app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department controller.
 * URI: /api/v1/departments
 *
 * @author Illia Aheyeu
 * @see Department
 */
@RestController
@RequestMapping(value = "/api/v1/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {
    /**
     * Interface allows manage {@link Department} objects
     */
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Find {@link Department} by its id.
     *
     * @param id {@link Department} id
     * @return {@link Department}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.findById(id), HttpStatus.OK);
    }

    /**
     * Find all {@link Department Departments} this specified or default pagination
     *
     * @param amount amount {@link Department} in page
     * @param page page
     * @return <code>List</code> of {@link Department Departments}
     */
    @GetMapping
    public ResponseEntity<List<Department>> findAll(@RequestParam(defaultValue = "10") int amount,
                                                    @RequestParam(defaultValue = "1") int page) {
        return new ResponseEntity<>(departmentService.findAll(amount, page), HttpStatus.OK);
    }

    /**
     * Create {@link Department}
     *
     * @param department {@link Department}
     * @return {@link Department}
     */
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
    }

    /**
     * Update {@link Department}
     *
     * @param department {@link Department}
     * @return Updated {@link Department}
     */
    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.update(department), HttpStatus.OK);
    }

    /**
     * Delete {@link Department}
     *
     * @param id {@link Department} id
     * @return {@link Department}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.delete(id), HttpStatus.OK);
    }
}
