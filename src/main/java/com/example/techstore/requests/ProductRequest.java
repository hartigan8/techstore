package com.example.techstore.requests;

import lombok.Data;

@Data
public class ProductRequest {
    
    private String name;
    private String category;
    private Integer price;
    private Integer quantity;
    private String description;
    private String photo;
}
