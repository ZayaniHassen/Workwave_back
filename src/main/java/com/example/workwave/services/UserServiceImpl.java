package com.example.workwave.services;


import com.example.workwave.entities.Gender;
import com.example.workwave.entities.Role;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.RoleRepository;
import com.example.workwave.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ServletContext context;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role etudiantRole = new Role();
        etudiantRole.setRoleName("Project manager");
        etudiantRole.setRoleDescription("Project manager role");
        roleRepository.save(etudiantRole);
        Role employee = new Role();
        employee.setRoleName("Employee");
        employee.setRoleDescription("employee role");
        roleRepository.save(employee);

        //Ajout de l'admin dans la base
        User adminUser = new User();
        adminUser.setFileName("slim961.jpg");
        adminUser.setPrenom("slim");
        adminUser.setNom("ayadi");
        adminUser.setEmail("slim.ayadi@esprit.tn");
        adminUser.setPassword(getEncodedPassword("slim"));
        adminUser.setUserName("slimayadi");
        adminUser.setGender(Gender.MALE);
        adminUser.setPhoneNumber(26821820);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public ResponseEntity<Map<String, String>> registerNewUser(User user) throws JsonProcessingException {
        try {
            Role role = roleRepository.findById("Employee").get();
            String token = RandomString.make(30);
            user.setToken(token);
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setPassword(getEncodedPassword(user.getPassword()));
            User savedUser = userRepository.save(user);

            if (savedUser != null) {
                // Send activation email to user
                String activationLink = "http://localhost:8090" + "/activate/" + token;
                String emailSubject = "Activate Your Account";
                String emailBody = "Dear " + savedUser.getUserName() + ",<br><br>" +
                        "Please click on the following link to activate your account:<br><br>" +
                        "<a href=\"" + activationLink + "\">" + activationLink + "</a><br><br>" +
                        "Best regards,<br>The Admin Team";

                sendEmail(savedUser.getEmail(), emailSubject, emailBody);

                Map<String, String> response = new HashMap<>();
                response.put("message", "ok");
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public byte[] getPhoto(String userName) throws Exception {
        User user = userRepository.findById(userName).get();
        String filePath = "src/main/resources/Face-Recog38/faces/" + user.getFileName();
        return Files.readAllBytes(Paths.get(filePath));
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserName()).orElse(null);
        existingUser.setUserName(user.getUserName());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }

    public User updateban(User user) {
        User existingUser = userRepository.findById(user.getUserName()).orElse(null);
        existingUser.setBan(user.isBan());


        return userRepository.save(existingUser);
    }

    public User updatetfa(User user) {
        User existingUser = userRepository.findById(user.getUserName()).orElse(null);
        existingUser.setTfa(user.isTfa());


        return userRepository.save(existingUser);
    }

    public User updateUserimage(String user, MultipartFile file) throws JsonProcessingException {
        User us = new ObjectMapper().readValue(user, User.class);
        User existingUser = userRepository.findById(us.getUserName()).orElse(null);

        System.out.println(us.getPassword());

        // Check if directory exists, create it if necessary
        String directoryPath = "src/main/resources/Face-Recog38/faces/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = file.getOriginalFilename();
        String newFileName = us.getUserName() + "-" + us.getUserName() + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(directoryPath + newFileName);
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

        existingUser.setFileName(newFileName);

        return userRepository.save(existingUser);
    }

    public boolean ifEmailExist(String mail) {
        return userRepository.existsByEmail(mail);
    }


    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(String userName) {
        userRepository.deleteById(userName);
        return "removed !! " + userName;
    }


    public User GetUserByUsername(String userName) {
        return userRepository.findById(userName).get();
    }

    public String updateToken(String token) throws UsernameNotFoundException {
        User user = userRepository.findByToken(token);
        if (user != null) {
            user.setToken(null);
            userRepository.save(user);
            return null;
        } else {
            throw new UsernameNotFoundException("Could not find any User with the token");
        }
    }


    public void updatePassword(User user, String newPassword) {

        user.setPassword(getEncodedPassword(newPassword));

        userRepository.save(user);
    }
    public List<User> GetUserByStatus(String etat){return userRepository.findByEtat(etat);}

}
