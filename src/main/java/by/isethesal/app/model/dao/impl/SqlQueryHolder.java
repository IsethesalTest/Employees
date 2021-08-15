package by.isethesal.app.model.dao.impl;

class SqlQueryHolder {
    private static final String PAGE_LIMIT_OFFSET = " LIMIT ? OFFSET ?";
    static final String DEPARTMENT_ID_KEY_HOLDER = "department_id";
    static final String EMPLOYEE_ID_KEY_HOLDER = "employee_id";
    /**
     * EMPLOYEE QUERIES
     */
    static final String FIND_EMPLOYEES = "SELECT employee_id, first_name, last_name, dp.department_id," +
            " dp.name, job_title, gender, date_of_birth FROM employees" +
            " INNER JOIN departments dp ON dp.department_id=employees.department_id";
    static final String FIND_EMPLOYEE_BY_ID = FIND_EMPLOYEES + " WHERE employee_id = ?";
    static final String FIND_ALL_EMPLOYEES = FIND_EMPLOYEES + PAGE_LIMIT_OFFSET;
    static final String DELETE_FROM_EMPLOYEE_BY_ID = "DELETE FROM employees WHERE employee_id = ?";
    static final String CREATE_EMPLOYEE = "INSERT INTO employees(first_name, last_name, department_id," +
            " job_title, gender, date_of_birth) VALUES(?, ?, ?, ?, ?, ?)";
    static final String UPDATE_EMPLOYEE = "UPDATE employees" +
            " SET first_name = COALESCE(?, first_name)," +
            " last_name = COALESCE(?, last_name)," +
            " department_id = COALESCE(?, department_id)," +
            " job_title = COALESCE(?, job_title)," +
            " gender = COALESCE(?, gender)," +
            " date_of_birth = COALESCE(?, date_of_birth)" +
            " WHERE employee_id = ?";
    /**
     * DEPARTMENT QUERIES
     */
    static final String FIND_DEPARTMENTS = "SELECT department_id, name FROM departments ";
    static final String FIND_ALL_DEPARTMENTS = FIND_DEPARTMENTS + PAGE_LIMIT_OFFSET;
    static final String CREATE_DEPARTMENT = " INSERT INTO departments(name) VALUES (?)";
    static final String FIND_DEPARTMENT_BY_NAME = FIND_DEPARTMENTS + " WHERE name = ?";
    static final String FIND_DEPARTMENT_BY_ID = FIND_DEPARTMENTS + " WHERE department_id = ?";
}
