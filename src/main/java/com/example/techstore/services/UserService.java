package com.example.techstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.User;
import com.example.techstore.repositories.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;

    public User saveOneUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getOneUser(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public void deleteOneUser(Long id) {
        userRepo.deleteById(id);
    }


}
