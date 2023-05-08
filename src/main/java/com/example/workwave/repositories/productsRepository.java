package com.example.workwave.repositories;

import com.example.workwave.entities.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface  productsRepository extends JpaRepository <products, Long> {



}