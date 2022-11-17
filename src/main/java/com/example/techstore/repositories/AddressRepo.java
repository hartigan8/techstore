package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.entities.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
    
}
