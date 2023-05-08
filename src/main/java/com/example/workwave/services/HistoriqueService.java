package com.example.workwave.services;

import com.example.workwave.entities.Historique;
import com.example.workwave.repositories.HistoriqueRepository;
import com.example.workwave.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueService {
    @Autowired
    HistoriqueRepository histoRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ServletContext context;


    public List<Historique> ShowHistorique() {
        return histoRepository.findAll();
    }

    public ResponseEntity<String> addHistorique(Historique f) {
        try {
            histoRepository.save(f);
            return new ResponseEntity<>("{\"message\": \"Historique saved successfully.\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Error saving Historique: " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteHistorique(Long idHistorique) {
        histoRepository.deleteById(idHistorique);
        return new ResponseEntity<>("{\"message\": \"Historique deleted successfully.\"}", HttpStatus.OK);
    }


    //the update method
    public Historique updateHistorique(Historique f) {

        return histoRepository.save(f);
    }

    public Optional<Historique> getHistorique(Long id) {
        return histoRepository.findById(id);
    }

    public List<Historique> findbyUser(String id) {

        return histoRepository.findByUser(userRepository.findById(id).get());
    }

    public Historique historiquebyuserandformation(String id, Long idforamtion) {
        return histoRepository.historiquebyuserandformation(id, idforamtion);
    }

}
