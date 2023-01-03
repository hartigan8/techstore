package com.example.techstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.entities.Log;
import com.example.techstore.entities.LogKey;


public interface LogRepo extends JpaRepository<Log, LogKey>{
    
}
