package com.example.workwave.services;

import com.example.workwave.entities.Cours;
import com.example.workwave.repositories.CoursRepository;
import com.example.workwave.repositories.FormationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    CoursRepository courRepo;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    ServletContext context;

    public List<Cours> ShowCours() {
        return courRepo.findAll();
    }

    public ResponseEntity<String> addCours(String c, MultipartFile file) throws JsonProcessingException {
        Cours payload = new ObjectMapper().readValue(c, Cours.class);

        try {
            String directoryPath = "src/main/webapp/Images/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filename = file.getOriginalFilename();
            String newFileName = filename + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(directoryPath + newFileName);
            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
            payload.setImage1(newFileName);
            courRepo.save(payload);
            return new ResponseEntity<>("{\"message\": \"Formation saved successfully.\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Error saving formation: " + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteCours(Long idCours) {
        courRepo.deleteById(idCours);
        return new ResponseEntity<>("{\"message\": \"Formation deleted successfully.\"}", HttpStatus.OK);
    }

    public Cours updateCours(String c, MultipartFile file) throws JsonProcessingException {

        Cours payload = new ObjectMapper().readValue(c, Cours.class);

        try {
            String directoryPath = "src/main/webapp/Images/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filename = file.getOriginalFilename();
            String newFileName = filename + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(directoryPath + newFileName);
            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
            payload.setImage1(newFileName);
            courRepo.save(payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return payload;

    }

    public Optional<Cours> getForm(Long id) {
        return courRepo.findById(id);
    }

    public Optional<Cours> getCours(Long idFormation) {

        return courRepo.findByFormation(formationRepository.findById(idFormation).get());
    }


    public byte[] getPhoto(Long id) throws Exception {
        Cours c = courRepo.findById(id).get();
        String filePath = "src/main/webapp/Images/" + c.getImage1();
        return Files.readAllBytes(Paths.get(filePath));
    }
}

