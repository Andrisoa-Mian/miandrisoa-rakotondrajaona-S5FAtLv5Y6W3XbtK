package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Classe;

import java.util.List;

public interface ClasseService{

    Classe creer(Classe classe);
    Classe modifier(Classe classe, Long id);
    List<Classe> afficherTout();
    void supprimer(Long id);
}
