package com.example.workwave.controllers;

import com.example.workwave.entities.Cours;
import com.example.workwave.repositories.CoursRepository;
import com.example.workwave.services.CoursService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class CoursController {
    @Autowired
    CoursService coursService;

    @Autowired
    CoursRepository coursRepo;


    @PostMapping("/addCours")
    public ResponseEntity<String> addCours(@RequestParam("cours") String cours, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        System.out.println(cours);
        return coursService.addCours(cours, file);
    }

    @DeleteMapping("/deleteCours/{id}")
    public ResponseEntity<String> deleteCours(@PathVariable long id) {
        System.out.println(id);
        return coursService.deleteCours(id);
    }

    @PutMapping("/updateCours")
    public Cours updateCours(@RequestParam("cours") String cours, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        return coursService.updateCours(cours, file);
    }

    @GetMapping("/ShowCours")//affichage+pagination
    public List<Cours> ShowCours() {
        return coursService.ShowCours();
    }

    @GetMapping("/ShowCours/{idCours}")
    public Optional<Cours> getCours(@PathVariable Long idCours) {
        return coursService.getForm(idCours);
    }

    @GetMapping("/CoursFormation/{idformation}")
    public Optional<Cours> CoursFormation(@PathVariable Long idformation) {
        return coursService.getCours(idformation);
    }

    @GetMapping(path = "/imgcours/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        return coursService.getPhoto(id);
    }
}
