package com.example.workwave.controllers;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.Formation;
import com.example.workwave.repositories.CategorieRepository;
import com.example.workwave.repositories.FormationRepository;
import com.example.workwave.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FormationController {
    @Autowired
    FormationService FormService;

    @Autowired
    FormationRepository FormRepository;
    @Autowired
    CategorieRepository CategRepository;

    @PostMapping("/addForm")
    public ResponseEntity<String> addForm(@RequestBody Formation payload) {
        return FormService.addForm(payload);
    }

    @DeleteMapping("/deleteForm/{id}")
    public ResponseEntity<String> deleteCateg(@PathVariable long id) {
        return FormService.deleteForm(id);
    }

    @PutMapping("/updateForm")
    public Formation updateCateg(@RequestBody Formation Form) {
        return FormService.updateForm(Form);
    }

    @GetMapping("/showForm")//affichage+pagination
    public List<Formation> show() {
        return FormService.ShowForm();
    }

    @GetMapping("/showForm/{idFormation}")
    public Optional<Formation> getFormation(@PathVariable Long idFormation) {
        return FormService.getForm(idFormation);
    }

}
