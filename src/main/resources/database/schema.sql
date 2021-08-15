create table departments
(
    department_id bigserial   not null
        constraint departments_pk
            primary key,
    name          varchar(50) not null
);

create table employees
(
    employee_id   bigserial   not null
        constraint employee_pk
            primary key,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    department_id bigint      not null
        constraint department_fk
            references departments
            on update cascade on delete set null,
    job_title     varchar(50),
    gender        varchar(50),
    date_of_birth timestamp   not null
);

create unique index employee_employee_id_uindex
    on employees (employee_id);

create unique index departments_department_id_uindex
    on departments (department_id);
