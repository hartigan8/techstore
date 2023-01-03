package com.example.techstore.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
