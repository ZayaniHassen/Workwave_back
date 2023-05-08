package com.example.workwave.services;


import com.example.workwave.entities.Role;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.RoleRepository;
import com.example.workwave.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
    public List<Role> getAllRole(){return roleRepository.findAll();}
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
    public ResponseEntity<?>deleterole(String id ){
        Role roleToDelete = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        List<User> adminUsers = userRepository.findByRole(roleToDelete);

        for (User user : adminUsers) {
            user.getRole().remove(roleToDelete);
            userRepository.save(user);
        }

        roleRepository.delete(roleToDelete);
        return ResponseEntity.ok(" roles deleted successfully");

    }

}
