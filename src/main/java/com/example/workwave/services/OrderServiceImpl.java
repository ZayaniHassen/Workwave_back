package com.example.workwave.services;

import com.example.workwave.entities.Order;
import com.example.workwave.entities.Supplier;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.OrderRepository;
import com.example.workwave.repositories.SupplierRepository;
import com.example.workwave.repositories.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    ServletContext context;
    @Autowired
    productsRepository productsRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    EntityManager entityManager;


    public List<Order> GetAllOrder() {
        return OrderRepository.findAll();
    }

    public String addOrder (Order o){

        OrderRepository.save(o);
        return "ok";
    }



    public String deleteOrder(long o_id) {
        OrderRepository.deleteById(o_id);
        return "Order removed !!" +o_id;
    }

    public Order updateOrder(Order order) {


        return OrderRepository.save(order);
    }
    public void addOrderToProduct(long o_id, long p_id) {
        Order order = OrderRepository.findById(o_id).orElse(null);
        products product = productsRepository.findById(p_id).orElse(null);
        if (order != null && product != null) {
            product.setOrder(order);
            productsRepository.save(product);
        }
    }
    public Order getorder(long o_id) {
        return entityManager.find(Order.class, o_id);

    }





}