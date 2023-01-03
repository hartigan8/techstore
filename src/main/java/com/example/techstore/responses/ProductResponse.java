package com.example.techstore.responses;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;

    private String name;

    private String category;

    private Integer price;

    private Integer quantity;
    
    private String description;

    
    private String photo;
}
