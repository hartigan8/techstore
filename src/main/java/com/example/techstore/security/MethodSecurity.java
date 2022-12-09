package com.example.techstore.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.techstore.entities.Address;
import com.example.techstore.entities.User;
import com.example.techstore.services.UserService;

@Component
public class MethodSecurity {
    @Autowired
    private UserService userService;

    public boolean isOwnedAddress(Long addressId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getOneUser(((UserDetailsImp) auth.getPrincipal()).getId());
        List<Address> adressesOfUser = user.getAddress();
        for (Address address : adressesOfUser) {
            if(address.getId() == addressId) return true;
        }
        return false;
    }
}
