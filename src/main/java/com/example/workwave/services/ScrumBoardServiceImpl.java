package com.example.workwave.services;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.ScrumBoard;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.ScrumBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ScrumBoardServiceImpl {

    @Autowired
    ScrumBoardRepository scrumBoardRepository;
    @Autowired
    ProjectRepository projectRepository;

    public List<ScrumBoard> GetAllScrumBoard() {
        return scrumBoardRepository.findAll();
    }

    public String addScrumBoard(ScrumBoard sb) {
        scrumBoardRepository.save(sb);
        return "ok";
    }


    public String deleteScrumBoard(Long Id) {
        scrumBoardRepository.deleteById(Id);
        return "SB removed !! " + Id;
    }


    //the update method
    public ScrumBoard updateScrumBoard(ScrumBoard scrumBoard) {
        return scrumBoardRepository.save(scrumBoard);
    }

    public ScrumBoard getScrumBoardById(Long id) {
        Optional<ScrumBoard> optionalScrumBoard = scrumBoardRepository.findById(id);
        if (optionalScrumBoard.isPresent()) {
            return optionalScrumBoard.get();
        } else {
            throw new EntityNotFoundException("ScrumBoard not found with id " + id);
        }
    }

    public ScrumBoard getScrumBoardByProjectId(Long id) {
        Project projet = projectRepository.findById(id).get();
        ScrumBoard optionalScrumBoard = scrumBoardRepository.findByProject(projet);
        return optionalScrumBoard;
    }
}