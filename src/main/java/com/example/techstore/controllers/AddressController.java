package com.example.techstore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.Address;
import com.example.techstore.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address saveOneAddress(@RequestBody Address address) {
        return addressService.saveOneAddress(address);
    }
    
}
