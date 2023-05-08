package com.example.workwave.controllers;

import com.example.workwave.entities.Supplier;
import com.example.workwave.repositories.SupplierRepository;
import com.example.workwave.services.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class SupplierController {

    @Autowired
    SupplierRepository  SupplierRepository;

    @Autowired
    SupplierServiceImpl Supplierservice;

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestBody Supplier Supplier ) {


        return Supplierservice.addSupplier(Supplier);

    }
    @DeleteMapping("/deleteSupplier/{id}")
    public String deleteSupplier(@PathVariable long id) {

        return Supplierservice.deleteSupplier(id);
    }
    @PutMapping("/updateSupplier")
    public Supplier updateSupplier(@RequestBody Supplier Supplier) {

        return Supplierservice.updateSupplier(Supplier);
    }
    @GetMapping("/Supplier")//affichage+pagination
    public List<Supplier> show() {
        return Supplierservice.GetAllSupplier();

    }
    @GetMapping(path = "/getsupplier/{Supplier}")
    public Supplier getsupplier(@PathVariable("Supplier") long id) throws Exception {
        return Supplierservice.getsupplier(id);
    }



}