package com.example.workwave.repositories;

import com.example.workwave.entities.Historique;
import com.example.workwave.entities.Quizz;
import com.example.workwave.entities.User;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HistoriqueRepository  extends JpaRepository<Historique,Long> {
    public List<Historique> findByUser(User user);
    @Query("SELECT h FROM Historique  h   WHERE h.user.userName =:userName AND h.formation.idFormation = :idFormation")
    Historique historiquebyuserandformation(@Param("userName") String userName, @Param("idFormation") Long idFormation);


}
