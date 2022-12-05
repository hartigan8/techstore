package com.example.techstore.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
}
