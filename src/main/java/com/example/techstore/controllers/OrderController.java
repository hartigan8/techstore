package com.example.techstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.techstore.entities.Order;
import com.example.techstore.requests.OrderRequest;
import com.example.techstore.services.OrderService;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('employee') or hasRole('admin')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("returnObject.user.id == principal.id or hasRole('employee') or hasRole('admin')")
    public Order getOneOrder(@PathVariable Long id) {
        return orderService.getOneOrder(id);
    }

    @PostMapping
    @PreAuthorize("returnObject.user.id == principal.id or hasRole('employee') or hasRole('admin')")
    public ResponseEntity saveOneOrder(@RequestBody OrderRequest order) {
        
        boolean valid = orderService.saveOneOrder(order);
        if(valid){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
