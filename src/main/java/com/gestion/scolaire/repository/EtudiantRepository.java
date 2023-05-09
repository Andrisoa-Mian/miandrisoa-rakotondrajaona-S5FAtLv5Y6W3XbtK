package com.gestion.scolaire.repository;

import com.gestion.scolaire.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    void deleteByClasseId(Long classeId);
    List<Etudiant> findByClasseId(Long classeId);

    List<Etudiant> findByClasseNomAndClasseEnseignantNom(String nomClasse, String nomEnseignant);
    List<Etudiant> findByClasseNom(String nomClasse);
    List<Etudiant> findByClasseEnseignantNom(String nomEnseignant);

    @Query("SELECT e FROM Etudiant e WHERE e.classe.enseignant.nom = :nom AND e.classe.enseignant.prenom = :prenom")
    List<Etudiant> findByEnseignantNomComplet(@Param("nom") String nom, @Param("prenom") String prenom);

}
