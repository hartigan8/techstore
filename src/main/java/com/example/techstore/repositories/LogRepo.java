package com.example.techstore.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.techstore.entities.Pglog;



@Repository
public interface LogRepo extends JpaRepository<Pglog, Long>{
    
    

}
