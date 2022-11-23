package com.example.techstore.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class OrderProductKey implements Serializable{
    private long orderId;
    private long productId;
}
