package com.example.workwave.controllers;

import com.example.workwave.entities.products;
import com.example.workwave.repositories.productsRepository;
import com.example.workwave.services.productsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class productsController {

    @Autowired
    productsRepository productsRepository;


    @Autowired
    productsServiceImpl productsservice;

    @PostMapping("/addproducts")
    public ResponseEntity<String> addproducts(@RequestBody products products) throws IOException, InterruptedException {
        String productName = products.getName();
        String[] command = {"C:/Users/MSI/AppData/Local/Programs/Python/Python310/python.exe", "src/main/sample_data/script.py", productName};

        // Create a new ProcessBuilder instance and start the process
        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();

        // Wait for the process to finish and get the exit code
        int exitCode = process.waitFor();

        // Check the exit code to see if the process completed successfully
        if (exitCode == 0) {
            products.setFilename(products.getName() + ".jpg");
            System.out.println("Python script completed successfully");
        } else {
            System.err.println("Python script failed with exit code " + exitCode);
        }

        String responseMessage = productsservice.addproducts(products);
        HttpStatus status = HttpStatus.OK; // set the status code to OK by default

        if (responseMessage.equals("error")) {
            status = HttpStatus.BAD_REQUEST; // set the status code to bad request if an error occurs
        }

        return ResponseEntity.status(status).body(responseMessage);
    }


    @DeleteMapping("/deleteproducts/{p_id}")
    public String deleteDepotproducts(@PathVariable long p_id) {

        return productsservice.deleteproducts(p_id);
    }


    @PutMapping("/updateproducts")
    public products updateproducts(@RequestBody products products) {

        return productsservice.updateproducts(products);
    }


    @GetMapping("/products")//affichage+pagination
    public List<products> show() {
        return productsservice.GetAllproducts();

    }


    @GetMapping("/productss")//affichage+pagination
    public Page<products> showPage(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageRequest = PageRequest.of(page, 2);
        return productsRepository.findAll(pageRequest);
    }

    @GetMapping(path = "/getproduct/{product}")
    public products getproduct(@PathVariable("product") long p_id) throws Exception {
        return productsservice.getproduct(p_id);
    }

    @GetMapping(path = "/Imgproduit/{p_id}")
    public byte[] getPhoto(@PathVariable("p_id") long p_id) throws Exception {
        return productsservice.getPhoto(p_id);
    }
}