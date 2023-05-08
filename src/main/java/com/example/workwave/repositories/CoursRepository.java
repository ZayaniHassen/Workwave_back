package com.example.workwave.repositories;

import com.example.workwave.entities.Cours;
import com.example.workwave.entities.Formation;
import com.example.workwave.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoursRepository extends JpaRepository<Cours,Long> {
    public Optional<Cours> findByFormation(Formation formation);

}