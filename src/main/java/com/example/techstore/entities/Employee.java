package com.example.techstore.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 20)
    private String name;
    
    @Column(nullable = false, length = 20)
    private String surname;

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false, length = 15)
    private String password;

    @OneToMany(mappedBy = "employee")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Order> orders;
}
