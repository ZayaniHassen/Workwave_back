package com.example.workwave.repositories;

import com.example.workwave.entities.Categorie;
import com.example.workwave.entities.holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {


}
