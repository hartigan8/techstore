package com.example.techstore.controllers;

import com.example.techstore.entities.Product;
import com.example.techstore.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveOneProduct(@RequestBody Product product) {
        
        return productService.saveOneProduct(product);
    }

    
}
