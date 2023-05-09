package com.gestion.scolaire.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "etudiant")
public class Etudiant extends Personne{
    @OneToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Builder
    public Etudiant() {
        super();
    }

    @Builder
    public Etudiant(String nom, String prenom) {
        super(nom, prenom);
    }

    @Builder
    public Etudiant(String nom, String prenom, Classe classe) {
        super(nom, prenom);
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", classe="+ classe +"]";
    }

}
