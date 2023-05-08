package com.example.workwave.controllers;

import com.example.workwave.entities.Quizz;
import com.example.workwave.repositories.QuizzRepository;
import com.example.workwave.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizzController {
    @Autowired
    QuizzService quizzService;

    @Autowired
    QuizzRepository quizzRepo;


    @PostMapping("/addQuizz")
    public ResponseEntity<String> addQuizz(@RequestBody Quizz payload) {
        return quizzService.addQuizz(payload);
    }

    @DeleteMapping("/deleteQuizz/{id}")
    public ResponseEntity<String> deleteQuizz(@PathVariable long id) {
        return quizzService.deleteQuizz(id);
    }

    @PutMapping("/updateQuizz")
    public Quizz updateQuizz(@RequestBody Quizz q) {
        return quizzService.updateQuizz(q);
    }

    @GetMapping("/ShowQuizz")//affichage+pagination
    public List<Quizz> ShowQuizz() {
        return quizzService.ShowQuizz();
    }

    @GetMapping("/quizbyformation/{id}")//affichage+pagination
    public Quizz quizbyformation(@PathVariable Long id) {
        return quizzService.quizbyformation(id);
    }

  @GetMapping("/quizbyid/{id}")//affichage+pagination
    public Quizz quizbyid(@PathVariable Long id) {
        return quizzRepo.findById(id).get();
    }


}
