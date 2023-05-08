package com.example.workwave.repositories;


import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {

}
