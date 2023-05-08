package com.example.workwave.controllers;

import com.example.workwave.entities.User;
import com.example.workwave.entities.Project;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController

public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectServiceImpl projectService;
    @PostMapping("/addProject")
    public String addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @DeleteMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable long id) {
        return projectService.deleteProject(id);
    }

    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

    @GetMapping("/Project")//affichage+pagination
    public List<Project> show() {
        return projectService.GetAllProject();
    }

    @GetMapping("/Project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

}