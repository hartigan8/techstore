package com.example.techstore.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}