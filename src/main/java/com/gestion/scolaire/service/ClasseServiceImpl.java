package com.gestion.scolaire.service;

import com.gestion.scolaire.model.Classe;
import com.gestion.scolaire.repository.ClasseRepository;
import com.gestion.scolaire.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClasseServiceImpl implements ClasseService{
    @Autowired
    ClasseRepository repository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Classe creer(Classe classe) {
        return repository.save(classe);
    }

    @Override
    public Classe modifier(Classe classe, Long id) {
        Optional<Classe> c = repository.findById(id);
        if(c.isEmpty()) return new Classe();
        Classe classeExistant = c.get();

        if(Objects.nonNull(classe.getNom()) && !"".equalsIgnoreCase(classe.getNom())){
            classeExistant.setNom(classe.getNom());
        }

        if(Objects.nonNull(classe.getEnseignant())){
            classeExistant.setEnseignant(classe.getEnseignant());
        }

        return repository.save(classeExistant);     }

    @Override
    public List<Classe> afficherTout() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void supprimer(Long classeId) {
        this.etudiantRepository.deleteByClasseId(classeId);
        this.repository.deleteById(classeId);
    }
}
