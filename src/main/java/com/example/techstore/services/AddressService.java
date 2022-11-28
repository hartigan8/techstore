package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

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
    public void deleteOneAddress(Long id) {
        addressRepo.deleteById(id);
    }
    public List<Address> getAllAdresses() {
        return addressRepo.findAll();
    }
    public Address updateOneAddress(Long id, Address newAddress){
        Optional<Address> address = addressRepo.findById(id);
        if(address.isPresent()){
            Address foundAddress = address.get();
            foundAddress.setCity(newAddress.getCity());
            foundAddress.setDistrict(newAddress.getDistrict());
            foundAddress.setStreet(newAddress.getStreet());
            foundAddress.setAddressExplanation(newAddress.getAddressExplanation());
            addressRepo.save(foundAddress);
            return foundAddress;
        }
        else{
            return null;
        }
    }
    public Address getOneAddress(Long id) {
        return addressRepo.findById(id).orElse(null);
    }
}
