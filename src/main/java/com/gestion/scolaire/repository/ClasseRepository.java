package com.gestion.scolaire.repository;

import com.gestion.scolaire.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    void deleteByEnseignantId(Long enseignantId);
    List<Classe> findByEnseignantId(Long enseignantId);

    @Modifying
    @Query("UPDATE Classe c SET c.enseignant = null WHERE c.enseignant.id = :enseignantId")
    void updateClasseEnseignantId(@Param("enseignantId") Long enseignantId);
}
