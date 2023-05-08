package com.example.workwave.services;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.ScrumBoard;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.ScrumBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Service

public class ProjectServiceImpl {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ScrumBoardRepository scrumrepository;

    @Autowired
    ServletContext context;

    public List<Project> GetAllProject() {
        return projectRepository.findAll();
    }

    public String addProject(Project c) {

        projectRepository.save(c);
        ScrumBoard scrum = new ScrumBoard();
        scrum.setProject(projectRepository.findByProjectname(c.getProjectname()));
        scrumrepository.save(scrum);
        return "ok";
    }

    public String deleteProject(Long idProject) {
        Project p = projectRepository.findById(idProject).get();
        scrumrepository.delete( scrumrepository.findByProject(p) );
        projectRepository.deleteById(idProject);
        return "project removed !! " + idProject;
    }


    //the update method
    public Project updateProject(Project project) {


        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new EntityNotFoundException("Project not found with id " + id);
        }
    }
}