package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Address;
import com.example.techstore.entities.User;
import com.example.techstore.repositories.UserRepo;
import com.example.techstore.requests.UserUpdateRequest;

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
    public User updateOneUser( UserUpdateRequest newUser){
        Optional<User> user = userRepo.findById(newUser.getId());
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setName(newUser.getName());
            foundUser.setSurname(newUser.getSurname());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setPhoneNumber(newUser.getPhoneNumber());
            userRepo.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }

    public User getOneUserByEmail(String email) {
        return userRepo.findByEmail(email);  
    }

    public List<Address> getUserAddresses(Long id) {
        return userRepo.findById(id).get().getAddress();
    }

    public List<User> getEmployees() {
        return userRepo.findByRole("employee");
    }
}
