package com.example.workwave.services;

import com.example.workwave.entities.Categorie;
import com.example.workwave.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    CategorieRepository CategRepository;

    @Autowired
    ServletContext context;


    public List<Categorie> GetAllCateg() {
        return CategRepository.findAll();
    }  public Optional<Categorie> getcateg(Long id ) {
        return CategRepository.findById(id);
    }

    public ResponseEntity addCateg(Categorie c)  {
        CategRepository.save(c);
        return new ResponseEntity<>("{\"message\": \"Category added successfully.\"}", HttpStatus.OK);
    }

    public String deleteCateg(Long idCateg) {
        CategRepository.deleteById(idCateg);
        return "Categorie removed !! " + idCateg;
    }


    //the update method
    public Categorie updateCateg(Categorie Categ) {


        return CategRepository.save(Categ);
    }
}
