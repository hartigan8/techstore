package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.entities.Log;

public interface LogRepo extends JpaRepository<Log, Long>{
    
}
