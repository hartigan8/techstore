package com.example.techstore.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.User;
import com.example.techstore.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public User saveOneUser(@RequestBody User user) {
        return userService.saveOneUser(user);
    }

    @GetMapping(value = {"/{id}"})
    public List<User> getAllUsers(@PathVariable(name = "id") Long id) {

        if(id == null) return userService.getAllUsers();
        else {
            List<User> uList = new LinkedList<>();
            uList.add(userService.getOneUser(id));
            return uList;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOneUser(@PathVariable Long id) {
        userService.deleteOneUser(id);
    }
    
    @GetMapping(value="/address")
    public void getAddress() {
        return userService.saveOneUser(null);
    }
    
}
