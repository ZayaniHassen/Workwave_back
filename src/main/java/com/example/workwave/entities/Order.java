package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name ="Orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  o_id;

    private int quantity;



    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date orderDate;

    @ManyToMany
    Set<Supplier> supplier;
    @OneToMany(mappedBy="order")
    @JsonManagedReference
    private Set<products> product;

    public long getO_id() {
        return o_id;
    }

    public void setO_id(long o_id) {
        this.o_id = o_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(Set<Supplier> supplier) {
        this.supplier = supplier;
    }

    public Set<products> getProduct() {
        return product;
    }

    public void setProduct(Set<products> product) {
        this.product = product;
    }

    public Order() {
    }

    public Order(long o_id, int quantity, Date orderDate, Set<Supplier> supplier) {
        this.o_id = o_id;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.supplier = supplier;
    }
}