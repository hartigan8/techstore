package com.example.techstore.repositories;

import com.example.techstore.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long>{
    
    public List<Product> findAllByCategory(String category);
}
