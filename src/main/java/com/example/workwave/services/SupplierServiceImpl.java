package com.example.workwave.services;

import com.example.workwave.entities.Supplier;
import com.example.workwave.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import java.util.List;
@Service
public class SupplierServiceImpl {

    @Autowired
    SupplierRepository SupplierRepository;

    @Autowired
    ServletContext context;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Supplier> GetAllSupplier() {
        return SupplierRepository.findAll();
    }

    public String addSupplier (Supplier s){
        SupplierRepository.save(s);
        return"ok" ;
    }

    public String deleteSupplier(long id) {
        SupplierRepository.deleteById(id);
        return "Supplier removed !!" +id;
    }

    public Supplier updateSupplier(Supplier Supplier) {
        Supplier existingSupplier = SupplierRepository.findById(Supplier.getId()).orElse(null);
        existingSupplier.setName(Supplier.getName());
        existingSupplier.setAddress(Supplier.getAddress());
        existingSupplier.setEmail(Supplier.getEmail());

        return SupplierRepository.save(existingSupplier);
    }
    public Supplier getsupplier(long supplierId) {
        return entityManager.find(Supplier.class, supplierId);
    }

}