package com.example.workwave.controllers;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.ScrumBoard;
import com.example.workwave.repositories.ProjectRepository;
import com.example.workwave.repositories.ScrumBoardRepository;
import com.example.workwave.services.ProjectServiceImpl;
import com.example.workwave.services.ScrumBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScrumBoardController {

    @Autowired
    ScrumBoardRepository scrumBoardRepository;

    @Autowired
    ScrumBoardServiceImpl scrumBoardService;

    @PostMapping("/addScrumBoard")
    public String addScrumBoard(@RequestBody ScrumBoard scrumBoard) { return scrumBoardService.addScrumBoard(scrumBoard); }



    @DeleteMapping("/deleteScrumBoard/{id}")
    public String deleteScrumBoard(@PathVariable long id) {
        return scrumBoardService.deleteScrumBoard(id);
    }

    @PutMapping("/updateScrumBoard")
    public ScrumBoard updateScrumBoard(@RequestBody ScrumBoard scrumBoard) {
        return scrumBoardService.updateScrumBoard(scrumBoard);
    }

    @GetMapping("/ScrumBoard")//affichage+pagination
    public List<ScrumBoard> show() {
        return scrumBoardService.GetAllScrumBoard();
    }

    @GetMapping("/ScrumBoard/{id}")
    public ScrumBoard getScrumBoardById(@PathVariable Long id) {
        return scrumBoardService.getScrumBoardById(id);
    }
    @GetMapping("/ScrumBoardbyproject/{id}")
    public ScrumBoard getScrumBoardByprojectId(@PathVariable Long id) {
        return scrumBoardService.getScrumBoardByProjectId(id);
    }
}