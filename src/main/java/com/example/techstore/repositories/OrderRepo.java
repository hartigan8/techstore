package com.example.techstore.repositories;

import com.example.techstore.entities.Order;

import java.util.List;

import com.example.techstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends JpaRepository<Order, Long> {

    public List<Order> findAllByShipped(Boolean shipped);

    @Query("SELECT o FROM orders o WHERE o.shipped = FALSE and o.employee.id = :employeeId")
    public List<Order> findAllUnshippedByEmployeeId(@Param("employeeId") Long employeeId);


    public List<Order> findAllByUser(User user);
}
