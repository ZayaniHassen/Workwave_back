package com.example.workwave.controllers;

import com.example.workwave.entities.Order;
import com.example.workwave.entities.Supplier;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.OrderRepository;
import com.example.workwave.repositories.SupplierRepository;
import com.example.workwave.repositories.productsRepository;
import com.example.workwave.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;


@RestController
public class OrderController {

    @Autowired
    OrderRepository OrderRepository;

    @Autowired
    OrderServiceImpl Orderservice;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    productsRepository productsRepository;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        Set<products> products = order.getProduct();
        if (products != null && !products.isEmpty()) {
            products product = products.iterator().next();
            long p_id = product.getP_id();
            System.out.println("p_id");
            System.out.println(p_id);
            // do something with p_id
            try {
                Set<Supplier> suppliers = order.getSupplier();
                if (suppliers != null && !suppliers.isEmpty()) {
                    Supplier supplier = suppliers.iterator().next();
                    String supplierEmail = supplier.getEmail();
                    sendMail(supplierEmail, product.getName(), order.getQuantity());
                    Orderservice.addOrder(order);
                    Orderservice.addOrderToProduct(order.getO_id(), p_id);
                    // do something with supplierEmail
                }

            } catch (MessagingException | UnsupportedEncodingException e) {
                return ("Failed to send email");
            }
        }
        return "ok";
    }

    public void sendMail(String recipientEmail, String p_name, int qte) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("slim.ayadi@esprit.tn", "workwave Support");
        helper.setTo(recipientEmail);

        String subject = "WorkWave Want to order From you";

        String content = "<p>Hello,</p>" +
                "<p>We would like to place an order with your company. " +
                "Please see the details of our order below:</p>" +
                "<ul>" +
                "<li>" + p_name + ":" + qte + " units</li>" +
                "</ul>" +
                "<p>Please confirm that you can fulfill this order and let us know the expected delivery date.</p>" +
                "<p>Thank you,</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @DeleteMapping("/deleteOrder/{o_id}")
    public String deleteOrder(@PathVariable long o_id) {

        return Orderservice.deleteOrder(o_id);
    }

    @PutMapping("/updateOrder")
    public Order updateOrder(@RequestBody Order Order) {

        return Orderservice.updateOrder(Order);
    }

    @GetMapping("/Order")//affichage+pagination
    public List<Order> show() {
        return Orderservice.GetAllOrder();

    }

    @GetMapping(path = "/getOrder/{Order}")
    public Order getorder(@PathVariable("Order") long o_id) throws Exception {
        return Orderservice.getorder(o_id);
    }


}