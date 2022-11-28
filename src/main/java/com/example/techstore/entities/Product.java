package com.example.techstore.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;
    
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<OrderProduct> orderProducts;
}
