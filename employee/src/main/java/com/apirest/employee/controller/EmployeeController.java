package com.apirest.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.employee.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestParam;
import com.apirest.employee.entity.*;
import com.apirest.employee.exception.*;


@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
            this.repository=repository;
    }

    @GetMapping("/employees")
   List<Employee> all(){
        return repository.findAll();
   }
   @PostMapping("/employees")
   Employee newEmployee(@RequestBody Employee employee) {
       //TODO: process POST request
       
       return repository.save(employee);
   }

   @GetMapping("/employees/{id}")
   Employee one (@PathVariable Long id) {
       return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
   }

   @PutMapping("employees/{id}")
   Employee replacEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        return repository.findById(id).
        map(employee->
        {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);

        })
        .orElseGet(()->{
            return repository.save(newEmployee);
        });

   }
   
   @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
   }
   


    

    

