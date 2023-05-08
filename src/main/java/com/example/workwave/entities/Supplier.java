package com.example.workwave.entities;

import com.example.workwave.entities.Order;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String name;

    private String address;

    private  String email;


    @ManyToMany(fetch = FetchType.LAZY)
    Set<Order> order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Supplier() {
    }

    public Supplier(long id, String name, String address, String email, Set<Order> order) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.order = order;
    }
}