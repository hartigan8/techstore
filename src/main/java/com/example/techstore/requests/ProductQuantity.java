package com.example.techstore.requests;

import lombok.Data;

@Data
public class ProductQuantity {
    private Long productId;
    private Integer quantity;
}
