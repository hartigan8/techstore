package com.example.techstore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.Address;
import com.example.techstore.services.AddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @DeleteMapping("/{id}")
    public void deleteOneAddress(@PathVariable Long id) {
        addressService.deleteOneAddress(id);
    }
    @GetMapping(value = {"/{id}"})
    public Address getOneUser(@PathVariable(name = "id") Long id) {
        Address address = addressService.getOneAddress(id);
         return address;  
    }
    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.getAllAdresses();
    }
    @PutMapping("{id}")
    public Address updateOneAddress(@PathVariable Long id, @RequestBody Address address){
        return addressService.updateOneAddress(id, address);
    }


    
}
