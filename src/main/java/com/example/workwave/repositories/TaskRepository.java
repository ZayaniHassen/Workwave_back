package com.example.workwave.repositories;

import com.example.workwave.entities.ScrumBoard;
import com.example.workwave.entities.Task;
import com.example.workwave.entities.User;
import com.example.workwave.entities.holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
}