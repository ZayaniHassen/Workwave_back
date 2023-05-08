package com.example.workwave.repositories;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.ScrumBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrumBoardRepository extends JpaRepository<ScrumBoard, Long> {
    public ScrumBoard findByProject(Project project) ;
}