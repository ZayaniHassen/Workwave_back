package com.example.workwave.controllers;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.Formation;
import com.example.workwave.services.CategorieService;
import com.example.workwave.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CategorieController {
    @Autowired
    CategorieService CategService;

    @Autowired
    CategorieRepository CategRepository;

    @PostMapping("/addCateg")
    public ResponseEntity addCateg(@RequestBody Map<String, Object> payload) {

        Categorie Categ = new Categorie();
        Categ.setNom((String) payload.get("nom"));
        return CategService.addCateg(Categ);
    }

    @DeleteMapping("/deleteCateg/{id}")
    public String deleteCateg(@PathVariable long id) {
        return CategService.deleteCateg(id);
    }

    @PutMapping("/updateCateg")
    public Categorie updateCateg(@RequestBody Categorie categ) {
        return CategService.updateCateg(categ);
    }


    @GetMapping("/Categs")//affichage+pagination
    public List<Categorie> show() {
        return CategService.GetAllCateg();
    }

    @GetMapping("/Categ/{id}")
    public Optional<Categorie> getCateg(@PathVariable Long id) {
        return CategService.getcateg(id);
    }

    @GetMapping("/categ/list")//affichage+pagination
    public Page<Categorie> showPage(@RequestParam(defaultValue = "0") int page) {
        PageRequest pageRequest = PageRequest.of(page, 2);
        System.out.println(CategRepository.findAll(pageRequest));
        return CategRepository.findAll(pageRequest);

    }

}
