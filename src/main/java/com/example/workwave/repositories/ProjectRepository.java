package com.example.workwave.repositories;

import com.example.workwave.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends JpaRepository<Project,Long> {
    public Project findByProjectname(String projectname);
}