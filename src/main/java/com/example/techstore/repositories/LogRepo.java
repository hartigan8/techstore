package com.example.techstore.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.entities.Log;
import com.example.techstore.entities.LogKey;



@Repository
public interface LogRepo extends JpaRepository<Log, LogKey>{
    
}
