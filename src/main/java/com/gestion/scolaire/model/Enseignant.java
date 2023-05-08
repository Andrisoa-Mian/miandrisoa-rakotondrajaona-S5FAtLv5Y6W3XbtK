package com.gestion.scolaire.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enseignant")
public class Enseignant extends Personne{

    @Builder
    public Enseignant() {super();}
    @Builder
    public Enseignant(String nom, String prenom) {
        super(nom,prenom);
    }

    @Override
    public String toString() {
        return "Enseignant [id=" + id + ", nom=" + nom + ", prenom" + prenom + "]";
    }
}
