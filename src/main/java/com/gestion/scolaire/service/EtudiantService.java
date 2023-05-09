package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EtudiantService {
    Etudiant creer(Etudiant etudiant);
    Etudiant modifier(Etudiant etudiant, Long id);
    List<Etudiant> afficherTout();
    void supprimer(Long id);
    Page<Etudiant> findEtudiantsByClasseAndEnseignant(String nomClasse,String enseignant,Pageable pageable);
}
