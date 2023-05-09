package com.gestion.scolaire;

import com.gestion.scolaire.model.Classe;
import com.gestion.scolaire.model.Enseignant;
import com.gestion.scolaire.model.Etudiant;
import com.gestion.scolaire.model.User;
import com.gestion.scolaire.repository.ClasseRepository;
import com.gestion.scolaire.repository.EnseignantRepository;
import com.gestion.scolaire.repository.EtudiantRepository;
import com.gestion.scolaire.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;

@Component
@Slf4j
public class ApiRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApiRunner.class);
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    UserRepository users;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        runJwtSecurity();

        Enseignant enseignant1 = new Enseignant("Isabelle","marine");
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

        logger.info("# enseignant: {}", enseignantRepository.count());

        logger.info("tous les enseignant non trier:");

        Iterable <Enseignant> enseignants = enseignantRepository.findAll();
        Iterator<Enseignant> ensIterator = enseignants.iterator();
        while (ensIterator.hasNext()) {
            logger.info("{}", ensIterator.next().toString());
        }

        logger.info("------------------------");


        logger.info("# etudiant: {}", etudiantRepository.count());

        logger.info("tous les etudiant non trier:");

        Iterable <Etudiant> etudiants = etudiantRepository.findAll();
        Iterator<Etudiant> etIterator = etudiants.iterator();
        while (etIterator.hasNext()) {
            logger.info("{}", etIterator.next().toString());
        }

        logger.info("------------------------");

    }

    public void runJwtSecurity(){
        this.users.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
        );

        this.users.save(User.builder()
                .username("jacob")
                .password(this.passwordEncoder.encode("0123456"))
                .roles(Arrays.asList( "ROLE_USER"))
                .build());

        this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
        );
        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
