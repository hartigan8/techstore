package com.example.techstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.techstore.entities.Order;
import com.example.techstore.services.OrderService;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOneOrder(@PathVariable Long id) {
        return orderService.getOneOrder(id);
    }

    @PostMapping
    public Order saveOneOrder(@RequestBody Order order) {
        return orderService.saveOneOrder(order);
    }

}
