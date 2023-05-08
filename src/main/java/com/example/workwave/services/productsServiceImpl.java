package com.example.workwave.services;

import com.example.workwave.entities.User;
import com.example.workwave.entities.products;
import com.example.workwave.repositories.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class productsServiceImpl {

    @Autowired
    productsRepository productsRepository;

    @Autowired
    ServletContext context;
    @PersistenceContext
    private EntityManager entityManager;

    public List<products> GetAllproducts() {
        return productsRepository.findAll();
    }

    public String addproducts (products p){
        productsRepository.save(p);
        return"ok" ;
    }

    public String deleteproducts(long p_id) {
        productsRepository.deleteById(p_id);
        return "product removed !!" +p_id;
    }

    public products updateproducts(products products) {
        products existingproducts = productsRepository.findById(products.getP_id()).orElse(null);
        existingproducts.setName(products.getName());
        existingproducts.setPrice(products.getPrice());
        existingproducts.setQuantity(products.getQuantity());




        return productsRepository.save(products);
    }


    public products getproduct(long productId) {
        return entityManager.find(products.class, productId);
    }


    public byte[] getPhoto(long p_id) throws Exception{
        products product  = productsRepository.findById(p_id).get();
        System.out.println(product.getFilename());
        String filePath = "src/main/sample_data/" + product.getFilename();
        return Files.readAllBytes(Paths.get(filePath));

    }




}