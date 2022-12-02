package com.example.techstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.techstore.repositories.UserRepo;
import com.example.techstore.security.UserDetailsImp;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return UserDetailsImp.create(userRepo.findByEmail(username));
    }
    
    public UserDetails loadUserById(Long id){
        return UserDetailsImp.create(userRepo.findById(id).get());
    }
}
