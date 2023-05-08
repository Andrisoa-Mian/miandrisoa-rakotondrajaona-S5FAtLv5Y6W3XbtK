package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Enseignant;

import java.util.List;

public interface EnseignantService{
    Enseignant creer(Enseignant enseignant);
    Enseignant modifier(Enseignant enseignant, Long id);
    List<Enseignant> afficherTout();
    void supprimer(Long id);
}
