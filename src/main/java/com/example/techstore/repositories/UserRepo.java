package com.example.techstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
    public User findByEmail(String username);

    public List<User> findByRole(String role);
    
}
