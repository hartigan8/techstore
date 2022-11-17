package com.example.techstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Address;
import com.example.techstore.repositories.AddressRepo;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepo addressRepo;

    public Address saveOneAddress(Address address) {
        return addressRepo.save(address);
    }
}
