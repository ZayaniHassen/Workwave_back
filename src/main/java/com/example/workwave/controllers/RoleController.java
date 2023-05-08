package com.example.workwave.controllers;


import com.example.workwave.entities.Role;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.RoleRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleterole(@PathVariable("id") String id) {
        return roleService.deleterole(id);
    }

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @GetMapping({"/getallroles"})
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @PostMapping("/users/{role}")
    public ResponseEntity<?> createUsersWithRoles(@RequestBody Map<String, Boolean> selectedUsers,
                                                  @PathVariable("role") String r) {
        Role rr = roleRepository.findById(r).get();
        Role noneRole = roleRepository.findRoleByRoleName("None");

        if (noneRole == null) {
            noneRole = new Role();
            noneRole.setRoleName("None");
            noneRole.setRoleDescription("None role");
            roleRepository.save(noneRole);
        }

        // Fetch all users from the repository
        List<User> userList = userRepository.findAll();

        // Loop through the user list and assign the role to each user
        for (User user : userList) {
            if (selectedUsers.getOrDefault(user.getUserName(), false)) {
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(rr);
                user.setRole(userRoles);
            } else {
                if (user.getRole().size() == 1 && user.getRole().contains(rr)) {
                    Set<Role> userRoles = new HashSet<>();
                    userRoles.add(noneRole);
                    user.setRole(userRoles);
                }
            }
        }

        // Save the updated user list to the database using a user repository
        userRepository.saveAll(userList);
        roleService.updateRole(rr);

        return ResponseEntity.ok("Users created with roles successfully");
    }
}
