package com.example.workwave.services;

import com.example.workwave.entities.Project;
import com.example.workwave.entities.Task;
import com.example.workwave.entities.holiday;
import com.example.workwave.repositories.HolidayRepository;
import com.example.workwave.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ServletContext context;


    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public String addTask(Task t)  {
        taskRepository.save(t);
        return "ok" ;
    }


    public String deleteTask(Long Id) {
        taskRepository.deleteById(Id);
        return "task removed !! " + Id;
    }


   /* public Task updateTask(Task task) {

        return taskRepository.save(task);
    }*/


    public Task updateTask(Task task) {

        return taskRepository.save(task);

    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new EntityNotFoundException("Task not found with id " + id);
        }
    }

}