package com.example.techstore.requests;

import lombok.Data;

@Data
public class AddressRegisterRequest {
    private String title;
    private String city;
    private String district;
    private String street;
    private String addressExplanation;
    private Long userId;
}
