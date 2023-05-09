package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Classe;
import com.gestion.scolaire.model.Enseignant;
import com.gestion.scolaire.model.Etudiant;
import com.gestion.scolaire.repository.ClasseRepository;
import com.gestion.scolaire.repository.EnseignantRepository;
import com.gestion.scolaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnseignantServiceImpl implements EnseignantService{
    @Autowired
    EnseignantRepository repository;
    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    EtudiantRepository etudiantRepository;
    @Override
    public Enseignant creer(Enseignant enseignant) {
        return repository.save(enseignant);
    }

    @Override
    public Enseignant modifier(Enseignant enseignant, Long id) {
        Optional<Enseignant> e = repository.findById(id);
        if(e.isEmpty()) return new Enseignant();
        Enseignant enseingantExistant = e.get();

        if(Objects.nonNull(enseignant.getNom()) && !"".equalsIgnoreCase(enseignant.getNom())){
            enseingantExistant.setNom(enseignant.getNom());
        }
        if(Objects.nonNull(enseignant.getPrenom()) && !"".equalsIgnoreCase(enseignant.getPrenom())){
            enseingantExistant.setPrenom(enseignant.getPrenom());
        }
        return repository.save(enseingantExistant);
    }

    @Override
    public List<Enseignant> afficherTout() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void supprimer(Long enseignantId) {
        List<Classe> classes = classeRepository.findByEnseignantId(enseignantId);
        classes.stream()
                .map(Classe::getId)
                .forEach(id -> {
                    List<Etudiant> etudiants = etudiantRepository.findByClasseId(id);
                    etudiants.forEach(etudiant -> etudiant.setClasse(etudiant.getClasse()));
                    etudiantRepository.saveAll(etudiants);
                });
        classeRepository.updateClasseEnseignantId(enseignantId);
        repository.deleteById(enseignantId);
    }
}
