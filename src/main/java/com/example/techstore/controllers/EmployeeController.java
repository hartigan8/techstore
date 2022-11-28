package com.example.techstore.controllers;

import com.example.techstore.entities.Employee;
import com.example.techstore.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getOneEmployee(@PathVariable Long id){
        return employeeService.getOneEmployee(id);
    }

    @PostMapping
    public Employee saveOneEmployee(@RequestBody Employee employee){
        return employeeService.saveOneEmployee(employee);
    }

    @PutMapping("{id}")
    public Employee updateOneEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateOneEmployee(id, employee);
    }

    @DeleteMapping ("{id}")
    public void deleteOneEmployee(@PathVariable Long id){
        employeeService.deleteOneEmployee(id);
    }
    
    
}
