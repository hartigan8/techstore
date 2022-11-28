package com.example.techstore.services;

import com.example.techstore.entities.Employee;
import com.example.techstore.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getOneEmployee(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee saveOneEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateOneEmployee(Long id, Employee employee) {
        Optional<Employee> employeeToUpdate = employeeRepo.findById(id);
        if(employeeToUpdate.isPresent()){
            Employee em = employeeToUpdate.get();
            em.setEmail(employee.getEmail());
            em.setName(employee.getName());
            em.setPassword(employee.getPassword());
            em.setPhoneNumber(employee.getPhoneNumber());
            em.setSurname(employee.getSurname());
            employeeRepo.save(em);
            return em;
        }
        else{
            return null;
        }
    }

    public void deleteOneEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}
