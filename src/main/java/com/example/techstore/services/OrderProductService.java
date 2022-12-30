package com.example.techstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.techstore.entities.Order;
import com.example.techstore.entities.OrderProduct;
import com.example.techstore.entities.OrderProductKey;
import com.example.techstore.repositories.OrderProductRepo;
import com.example.techstore.repositories.ProductRepo;
import com.example.techstore.requests.ProductQuantity;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepo orderProductRepo;
    @Autowired
    private ProductRepo productRepo;
    public List<OrderProduct> getListOfProducts(List<OrderProduct> orderProducts) {
        return orderProductRepo.saveAll(orderProducts);
    }


    public void saveOrderDetails(List<ProductQuantity> orderList, Order order) {
        for (ProductQuantity productQuantity : orderList) {
            OrderProduct op = new OrderProduct();
            OrderProductKey key = new OrderProductKey();
            key.setOrderId(order.getId());
            key.setProductId(productQuantity.getProductId());
            op.setKey(key);
            op.setOrder(order);
            op.setProduct(productRepo.findById(productQuantity.getProductId()).get());
            orderProductRepo.save(op);
        }
    }
}
