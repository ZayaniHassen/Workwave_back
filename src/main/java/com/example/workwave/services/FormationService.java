package com.example.workwave.services;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.Formation;
import com.example.workwave.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Service
public class FormationService {
    @Autowired
    FormationRepository FormRepository;


    @Autowired
    ServletContext context;


    public List<Formation> ShowForm() {
        return FormRepository.findAll();
    }

    public ResponseEntity<String> addForm(Formation f) {
        try {
            FormRepository.save(f);
            return new ResponseEntity<>("{\"message\": \"Formation saved successfully.\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Error saving formation: " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteForm(Long idFormation) {
        FormRepository.deleteById(idFormation);
        return new ResponseEntity<>("{\"message\": \"Formation deleted successfully.\"}", HttpStatus.OK);
    }


    //the update method
    public Formation  updateForm(Formation f) {

        return FormRepository.save(f);
    }
    public Optional<Formation> getForm(Long id){
        return FormRepository.findById(id);
    }


}
