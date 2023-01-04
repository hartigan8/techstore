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
    @PreAuthorize("hasAuthority('employee') or hasAuthority('admin')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("returnObject.user.id == principal.id or hasAuthority('employee') or hasAuthority('admin')")
    public Order getOneOrder(@PathVariable Long id) {
        return orderService.getOneOrder(id);
    }

    @PostMapping
    
    public ResponseEntity<String> saveOneOrder(@RequestBody OrderRequest order) {
        
        boolean valid = orderService.saveOneOrder(order);
        if(valid){
            return new ResponseEntity<>("Order accepted.", HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>("Something is wrong.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/markAsShipped/{orderId}")
    @PreAuthorize("hasAuthority('employee') or hasAuthority('admin')")
    public Order markAsShipped(@PathVariable Long orderId) {
        return orderService.markAsShipepd(orderId);
    }

    @GetMapping("/unshippeds/")
    @PreAuthorize("hasAuthority('admin')")
    public List<Order> unshipped() {
        return orderService.unshippeds();
    }

    @GetMapping("/unshipped/{employeeId}")
    @PreAuthorize("#employeeId == principal.id and hasAuthority('employee') or hasAuthority('admin')")
    public List<Order> getUnshippedOrderByEmployee(@PathVariable Long employeeId) {
        return orderService.getUnshippedOrderByEmployee(employeeId);
    }

    @GetMapping("/myOrders")
    public List<Order> getMyOrders() {
        return orderService.getMyOrders();
    }
}
