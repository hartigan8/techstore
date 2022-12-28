package com.example.techstore.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Order;
import com.example.techstore.repositories.OrderRepo;
import com.example.techstore.requests.OrderRequest;

@Service
public class OrderService {


    private OrderRepo orderRepo;
    private ProductService productService;
    private OrderProductService orderProductService;

    

    public OrderService(OrderRepo orderRepo, ProductService productService, OrderProductService orderProductService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order getOneOrder(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public boolean saveOneOrder(OrderRequest order) {
        boolean valid = productService.checkQuantities(order.getOrderList());
        if(valid){
            // make payment

            //////
            productService.updateQuantities(order.getOrderList());
            Order orderToSave = new Order();
            orderToSave.setAddress(order.getAddress());
            orderToSave.setDate(new Date());
            Order savedOrder = orderRepo.save(orderToSave);
            orderProductService.saveOrderDetails(order.getOrderList(), savedOrder);
            return true;
        }
        else return false;
    }

}
