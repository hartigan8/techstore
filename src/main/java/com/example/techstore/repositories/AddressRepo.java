package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{
    
}
