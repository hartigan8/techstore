package com.example.techstore.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.techstore.entities.Address;
import com.example.techstore.entities.Order;
import com.example.techstore.repositories.AddressRepo;
import com.example.techstore.repositories.OrderRepo;
import com.example.techstore.requests.OrderRequest;

@Service
public class OrderService {


    private OrderRepo orderRepo;
    private ProductService productService;
    private OrderProductService orderProductService;
    private AddressRepo addressRepo;
    

    

    public OrderService(OrderRepo orderRepo, ProductService productService, OrderProductService orderProductService,
            AddressRepo addressRepo) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.addressRepo = addressRepo;
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
            Optional<Address> oA = addressRepo.findById(order.getAddressId());
            if(!oA.isPresent()){
                return false;
            }
            orderToSave.setAddress(oA.get());
            orderToSave.setDate(new Date());
            Order savedOrder = orderRepo.save(orderToSave);
            orderProductService.saveOrderDetails(order.getOrderList(), savedOrder);
            return true;
        }
        else return false;
    }

    public Order markAsShipepd(Long orderId) {
        Optional<Order> oO = orderRepo.findById(orderId);
        if(oO.isPresent()){
            Order o = oO.get();
            o.setShipped(true);
            orderRepo.save(o);
            return o;
        }
        return null;
    }

    public List<Order> unshippeds() {
        return orderRepo.findAllByShipped(false);
    }

    public List<Order> getUnshippedOrderByEmployee(Long employeeId) {
        return orderRepo.findAllUnshippedByEmployeeId(employeeId);
    }

}
