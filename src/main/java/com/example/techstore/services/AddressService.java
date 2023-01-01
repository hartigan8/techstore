package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Address;
import com.example.techstore.repositories.AddressRepo;
import com.example.techstore.repositories.UserRepo;
import com.example.techstore.requests.AddressRegisterRequest;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private UserRepo userRepo;

    public Address saveOneAddress(AddressRegisterRequest address) {
        Address addressToSave = new Address();
        addressToSave.setTitle(address.getTitle());
        addressToSave.setAddressExplanation(address.getAddressExplanation());
        addressToSave.setCity(address.getCity());
        addressToSave.setDistrict(address.getDistrict());
        addressToSave.setStreet(address.getStreet());
        addressToSave.setUser(userRepo.findById(address.getUserId()).get());
        return addressRepo.save(addressToSave);
    }
    public void deleteOneAddress(Long id) {
        addressRepo.deleteById(id);
    }
    public List<Address> getAllAdresses() {
        return addressRepo.findAll();
    }
    public Address updateOneAddress(Long id, AddressRegisterRequest newAddress){
        Optional<Address> address = addressRepo.findById(id);
        if(address.isPresent()){
            Address foundAddress = address.get();
            foundAddress.setTitle(newAddress.getTitle());
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
