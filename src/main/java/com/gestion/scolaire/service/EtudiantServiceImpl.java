package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Etudiant;
import com.gestion.scolaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService{
    @Autowired
    EtudiantRepository repository;

    @Override
    public Etudiant creer(Etudiant etudiant) {
        return repository.save(etudiant);
    }
    @Override
    public Etudiant modifier(Etudiant etudiant, Long id) {
        Optional<Etudiant> e = repository.findById(id);
        if(e.isEmpty()) return new Etudiant();
        Etudiant etudiantExistant = e.get();

        if(Objects.nonNull(etudiant.getNom()) && !"".equalsIgnoreCase(etudiant.getNom())){
            etudiantExistant.setNom(etudiant.getNom());
        }
        if(Objects.nonNull(etudiant.getPrenom()) && !"".equalsIgnoreCase(etudiant.getPrenom())){
            etudiantExistant.setPrenom(etudiant.getPrenom());
        }
        if(Objects.nonNull(etudiant.getClasse())){
            etudiantExistant.setClasse(etudiant.getClasse());
        }
        return repository.save(etudiantExistant);
    }

    @Override
    public List<Etudiant> afficherTout() {
        return repository.findAll();
    }

    @Override
    public void supprimer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Etudiant> findEtudiantsByClasseAndEnseignant(String nomClasse, String enseignant, Pageable pageable) {
        List<Etudiant> etudiants = new ArrayList<>();

        String prenomEnseignant = null;
        String nomEnseignant = null;

        if(Objects.nonNull(enseignant)){
            String[] enseignantParts = enseignant.split(" ");
            prenomEnseignant = enseignantParts[0];
            nomEnseignant = enseignantParts[1];        }

        if(Objects.isNull(nomClasse) && Objects.isNull(nomEnseignant) && Objects.isNull(prenomEnseignant)){
            return repository.findAll(pageable);
        }

        if (Objects.nonNull(nomClasse)) {
            etudiants.addAll(repository.findByClasseNom(nomClasse.toUpperCase()));
        }
        if (Objects.nonNull(nomEnseignant) && Objects.nonNull(prenomEnseignant)) {
            etudiants.addAll(repository.findByEnseignantNomComplet(nomEnseignant,prenomEnseignant));
        }

        return new PageImpl<>(etudiants, pageable, etudiants.size());
    }
}
