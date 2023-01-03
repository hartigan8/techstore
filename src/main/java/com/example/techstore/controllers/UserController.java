package com.example.techstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.Address;
import com.example.techstore.entities.User;
import com.example.techstore.requests.UserUpdateRequest;
import com.example.techstore.services.UserService;

@RestController
@RequestMapping("user") 
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public User saveOneUser(@RequestBody User user) {
        return userService.saveOneUser(user);
    }

    @GetMapping(value = {"/{id}"})
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('admin')")
    public User getOneUser(@PathVariable(name = "id") Long id) {
        User user = userService.getOneUser(id);
         return user;
        
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public List<User> getAllusers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('admin')")
    public void deleteOneUser(@PathVariable Long id) {
        userService.deleteOneUser(id);
    }

    @PutMapping
    @PreAuthorize("#updateBody.id == authentication.principal.id or hasAuthority('admin')")
    public User updateOneUser(@RequestBody UserUpdateRequest updateBody){
        return userService.updateOneUser(updateBody);
        
    }   
    
    @GetMapping("/{id}/addresses")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('admin')")
    public List<Address> getUserAddresses(@PathVariable Long id) {
        return userService.getUserAddresses(id);
    }
    
    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('admin')")
    public List<User> getEmployees() {
        return userService.getEmployees();
    }
}
