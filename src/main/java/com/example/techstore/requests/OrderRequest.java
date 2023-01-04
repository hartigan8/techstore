package com.example.techstore.requests;

import java.util.List;


import lombok.Data;

@Data
public class OrderRequest {
    private Long addressId;
    private List<ProductQuantity> orderList;
}
