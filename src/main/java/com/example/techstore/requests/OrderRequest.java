package com.example.techstore.requests;

import java.util.List;

import com.example.techstore.entities.Address;

import lombok.Data;

@Data
public class OrderRequest {
    private Address address;
    private List<ProductQuantity> orderList;
}
