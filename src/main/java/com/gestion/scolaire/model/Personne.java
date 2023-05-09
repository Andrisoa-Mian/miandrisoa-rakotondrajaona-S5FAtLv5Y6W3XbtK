package com.gestion.scolaire.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "prenom")
    protected String prenom;

    @Column(name = "nom")
    protected String nom;

    public Personne(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }
}
