package com.example.techstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.User;
import com.example.techstore.services.UserService;

@RestController
@RequestMapping("user") //path: /user
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public User saveOneUser(@RequestBody User user) {
        return userService.saveOneUser(user);
    }

    @GetMapping(value = {"/{id}"})
    public User getOneUser(@PathVariable(name = "id") Long id) {
        User user = userService.getOneUser(id);
         return user;
        
    }
    @GetMapping
    public List<User> getAllusers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteOneUser(@PathVariable Long id) {
        userService.deleteOneUser(id);
    }

    @PutMapping("/{id}")
    public User updateOneUser(@PathVariable Long id, @RequestBody User newUser){
        return userService.updateOneUser(id,newUser);
        
    }   
    
    
    
}
