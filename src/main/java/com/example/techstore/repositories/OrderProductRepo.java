package com.example.techstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.entities.OrderProduct;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Long>{
    
}
