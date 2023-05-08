package com.gestion.scolaire.repository;

import com.gestion.scolaire.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
    Optional<Enseignant> findByNom(String nom);
}
