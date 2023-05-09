package com.gestion.scolaire.controller;

import com.gestion.scolaire.model.Classe;
import com.gestion.scolaire.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "classe")
public class ClasseController {
    @Autowired
    ClasseService service;

    @PostMapping("/creer")
    Classe creerClasse(@RequestBody Classe classe){
        return service.creer(classe);
    }
    @PutMapping("/modifier/{id}")
    Classe modifierClasse(@RequestBody Classe classe, @PathVariable Long id){
        return service.modifier(classe,id);
    }

    @GetMapping("/list")
    List<Classe> afficherListClasse(){
        return service.afficherTout();
    }
    @DeleteMapping("/supprimer/{id}")
    void supprimerClasse(@PathVariable Long id){
        service.supprimer(id);
    }
}
