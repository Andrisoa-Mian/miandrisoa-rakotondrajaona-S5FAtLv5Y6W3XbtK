package com.gestion.scolaire;

import com.gestion.scolaire.model.Classe;
import com.gestion.scolaire.model.Enseignant;
import com.gestion.scolaire.model.Etudiant;
import com.gestion.scolaire.repository.ClasseRepository;
import com.gestion.scolaire.repository.EnseignantRepository;
import com.gestion.scolaire.repository.EtudiantRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@EnableAsync
public abstract class TestUnitaire {
    @Autowired
    ClasseRepository repository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private ClasseRepository classeRepository;

    @Autowired public MockMvc mvc;

    @PostConstruct
    public void init(){
        Enseignant enseignant1 = new Enseignant("Isabelle","martine");
        Enseignant enseignant2 = new Enseignant("Jacob","stewart");

        enseignantRepository.save(enseignant1);
        enseignantRepository.save(enseignant2);

        Classe classeL = new Classe("CM1", enseignant1);
        Classe classeS = new Classe("CP2", enseignant2);

        classeRepository.save(classeL);
        classeRepository.save(classeS);

        etudiantRepository.save(new Etudiant("axel","riks",classeS));
        etudiantRepository.save(new Etudiant("alexandre","john",classeL));
        etudiantRepository.save(new Etudiant("richard","smith",classeL));
    }
}
