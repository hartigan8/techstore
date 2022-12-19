package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    
    public User findByEmail(String username);
    
}
