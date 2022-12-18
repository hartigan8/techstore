package com.example.techstore.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techstore.entities.User;
import com.example.techstore.requests.LoginRequest;
import com.example.techstore.requests.RegisterRequest;
import com.example.techstore.responses.AuthResponse;
import com.example.techstore.security.JwtTokenProvider;
import com.example.techstore.services.UserService;


@RestController
@RequestMapping("auth")
public class AuthController {

    private UserService userService;
    private PasswordEncoder encoder;
    private AuthenticationManager authManager;
    private JwtTokenProvider tokenProvider;


    
    
    public AuthController(UserService userService, PasswordEncoder encoder, AuthenticationManager authManager,
            JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.encoder = encoder;
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
    }




    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        if(auth.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            return new ResponseEntity<>("Bearer " + tokenProvider.generateJwtToten(auth), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if(userService.getOneUserByEmail(request.getEmail()) != null){
            return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("user");
        userService.saveOneUser(user);
        
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = "Bearer " + tokenProvider.generateJwtToten(auth);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
