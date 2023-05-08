package com.example.workwave.controllers;

import com.example.workwave.entities.Historique;
import com.example.workwave.repositories.HistoriqueRepository;
import com.example.workwave.repositories.FormationRepository;
import com.example.workwave.services.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HistoriqueController {
    @Autowired
    HistoriqueService historiqueService;

    @Autowired
    HistoriqueRepository historiqueRepo;


    @PostMapping("/addHistorique")
    public ResponseEntity<String> addHistorique(@RequestBody Historique payload) {
        return historiqueService.addHistorique(payload);
    }

    @DeleteMapping("/deleteHistorique/{id}")
    public ResponseEntity<String> deleteHistorique(@PathVariable long id) {
        return historiqueService.deleteHistorique(id);
    }

    @PutMapping("/updateHistorique")
    public Historique updateHistorique(@RequestBody Historique c) {
        return historiqueService.updateHistorique(c);
    }

    @GetMapping("/ShowHistorique")//affichage+pagination
    public List<Historique> ShowHistorique() {
        return historiqueService.ShowHistorique();
    }

    @GetMapping("/ShowHistorique/{idHistorique}")
    public Optional<Historique> getHistorique(@PathVariable Long idHistorique) {
        return historiqueService.getHistorique(idHistorique);
    }

    @GetMapping("/findbyUser/{userName}")
    public List<Historique> findbyUser(@PathVariable String userName) {
        return historiqueService.findbyUser(userName);
    }

    @GetMapping("/historiquebyuserandformation/{userName}/{idformation}")
    public Historique historiquebyuserandformation(@PathVariable String userName, @PathVariable Long idformation) {
        return historiqueService.historiquebyuserandformation(userName, idformation);
    }
}
