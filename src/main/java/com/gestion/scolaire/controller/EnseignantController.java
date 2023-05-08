package com.gestion.scolaire.controller;


import com.gestion.scolaire.model.Enseignant;
import com.gestion.scolaire.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "enseignant")
public class EnseignantController {
    @Autowired
    EnseignantService service;

    @PostMapping("/creer")
    Enseignant creerEnseignant(@RequestBody Enseignant enseignant) {
        return service.creer(enseignant);
    }

    @PutMapping("/modifier/{id}")
    Enseignant modifierEnseignant(@RequestBody Enseignant enseignant, @PathVariable Long id) {
        return service.modifier(enseignant, id);
    }

    @GetMapping("/list")
    List<Enseignant> afficherListEnseignant() {
        return service.afficherTout();
    }

    @DeleteMapping("/supprimer/{id}")
    void supprimerEnseignant(@PathVariable Long id) {
        service.supprimer(id);
    }
}
