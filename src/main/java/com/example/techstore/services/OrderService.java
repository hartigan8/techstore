package com.example.techstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Order;
import com.example.techstore.repositories.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getOneOrder(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Order saveOneOrder(Order order) {
        return orderRepo.save(order);
    }

}
