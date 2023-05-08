package com.gestion.scolaire.controller;

import com.gestion.scolaire.model.Etudiant;
import com.gestion.scolaire.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "etudiant")
public class EtudiantController {
    @Autowired
    EtudiantService service;

    @PostMapping("/creer")
    Etudiant creerEtudiant(@RequestBody Etudiant etudiant){
        return service.creer(etudiant);
    }
    @PutMapping("/modifier/{id}")
    Etudiant modifierEtudiant(@RequestBody Etudiant etudiant, @PathVariable Long id){
        return service.modifier(etudiant,id);
    }

    @GetMapping("/list")
    List<Etudiant> afficherListEtudiant(){
        List<Etudiant> list = service.afficherTout();
        return list;
    }
    @DeleteMapping("/supprimer/{id}")
    void supprimerEtudiant(@PathVariable Long id){
        service.supprimer(id);
    }

    @GetMapping("/rechercher")
    public Page<Etudiant> rechercherEtudiant(@RequestParam(name = "classe", required = false) String classe,
                                             @RequestParam(name = "enseignant", required = false) String enseignant,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size) {
        return service.findEtudiantsByClasseAndEnseignant(classe,enseignant,PageRequest.of(page, size));
    }
}
