package com.example.workwave.controllers;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.ScrumBoard;
import com.example.workwave.entities.Task;
import com.example.workwave.repositories.HolidayRepository;
import com.example.workwave.repositories.TaskRepository;
import com.example.workwave.services.HolidayServiceImpl;
import com.example.workwave.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskServiceImpl taskService;

    @PostMapping("/addTask")
    public String addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable long id) {
        return taskService.deleteTask(id);
    }


    @PutMapping("/updateTask")
    public Task updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }


    @GetMapping("/Task")//affichage+pagination
    public List<Task> show() {
        return taskService.getAllTask();
    }

    @GetMapping("/Task/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/changeetat/{id}/{etat}")
    public Task changeTaskStatus(@PathVariable long id, @PathVariable String etat) {
        Task task = taskRepository.findById(id).get();
        System.out.println("task*********************************************");
        System.out.println(task);
        task.setEtat(etat);
        taskRepository.save(task);

        return task;
    }

}