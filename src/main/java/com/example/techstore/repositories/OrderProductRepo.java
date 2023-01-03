package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.techstore.entities.OrderProduct;


public interface OrderProductRepo extends JpaRepository<OrderProduct, Long>{
    
}
