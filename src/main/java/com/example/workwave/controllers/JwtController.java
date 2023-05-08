package com.example.workwave.controllers;


import com.example.workwave.entities.JwtRequest;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.JwtService;
import com.example.workwave.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("request");
        System.out.println(jwtRequest.getUserName());
        System.out.println(jwtRequest.getPassword());
        return jwtService.createJwtToken(jwtRequest);
    }


    @PostMapping({"/faceAuthenticate"})
    public ResponseEntity<?> aa(@RequestBody User user) throws Exception {
        User u = userRepository.findById(user.getUserName()).get();
        System.out.println(u.isTfa());
        if(u.isTfa()==true) {
            String scriptPath = "src/main/resources/Face-Recog38/reco.py";
            String pythonPath = "C:/Users/MSI/AppData/Local/Programs/Python/Python310/python.exe";// wheere pythin
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line.trim()).append("\n");
            }
            System.out.println(output);

            if (output.toString().startsWith("authenticated")) {
                String userId = output.toString().split(" ")[1];
                System.out.println("User ID: " + userId);
                System.out.println("User ID:2 " + u.getUserName().toString());

                if (u.getUserName().equals(userId.trim())) {
                    System.out.println("Face authenticated");
                    // return an access token and user object
                    return jwtService.createJwtTokenFace(userId.trim());
                } else {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "not authenticated");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                }
            } else {
                System.out.println("nrm");
                Map<String, Object> response = new HashMap<>();
                response.put("message", "not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        else{
            Map<String, Object> response = new HashMap<>();
            response.put("message", "tfa disabled");
            return ResponseEntity.status(403).body(response);
        }
    }
  /*  @PostMapping({"/addproduct"})
    public ResponseEntity<?> aa(@RequestBody products p) throws Exception {
            String scriptPath = "src/main/resources/Face-Recog38/reco.py";
            String pythonPath = "C:/Users/MSI/AppData/Local/Programs/Python/Python310/python.exe";// wheere pythin
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line.trim()).append("\n");
            }
            System.out.println(output);

            if (output.toString().startsWith("authenticated")) {
                String userId = output.toString().split(" ")[1];
                System.out.println("User ID: " + userId);
                System.out.println("User ID:2 " + u.getUserName().toString());

                if (u.getUserName().equals(userId.trim())) {
                    System.out.println("Face authenticated");
                    // return an access token and user object
                    return jwtService.createJwtTokenFace(userId.trim());
                } else {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "not authenticated");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                }
            } else {
                System.out.println("nrm");
                Map<String, Object> response = new HashMap<>();
                response.put("message", "not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }

    }*/
}




