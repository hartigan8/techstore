package com.example.techstore.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.Address;
import com.example.techstore.requests.AddressRegisterRequest;
import com.example.techstore.services.AddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
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
    @PreAuthorize("#address.userId == principal.id or hasAuthority('admin')")
    public Address saveOneAddress(@RequestBody AddressRegisterRequest address) {
        return addressService.saveOneAddress(address);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@methodSecurity.isOwnedAddress(#id) or hasAuthority('admin')")
    public void deleteOneAddress(@PathVariable Long id) {
        addressService.deleteOneAddress(id);
    }

    
    @GetMapping(value = {"/{id}"})
    @PostAuthorize("returnObject.user.id == principal.id or hasRole('admin')")
    public Address getOneAddress(@PathVariable Long id) {
        return addressService.getOneAddress(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('employee') or hasAuthority('admin')")
    public List<Address> getAllAddresses(){
        return addressService.getAllAdresses();
    }
    
    @PutMapping("{id}")
    @PreAuthorize("#address.userId == principal.id or hasAuthority('admin')")
    public Address updateOneAddress(@PathVariable Long id, @RequestBody AddressRegisterRequest address){
        return addressService.updateOneAddress(id, address);
    }


    
}
