package com.example.techstore.responses;
import lombok.Data;

@Data
public class AuthResponse{
    String message;
    Long userId;
    String name;
    String role;
}