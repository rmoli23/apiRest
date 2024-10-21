package com.apirest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apirest.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}