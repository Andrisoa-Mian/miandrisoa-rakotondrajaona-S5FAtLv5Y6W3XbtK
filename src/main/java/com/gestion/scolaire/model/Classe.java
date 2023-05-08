package com.gestion.scolaire.model;

import lombok.*;

import javax.persistence.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classe")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    @OneToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @Builder
    public Classe(String nom) {
        this.nom = nom;
    }

    @Builder
    public Classe(String nom, Enseignant enseignant) {
        this.nom = nom;
        this.enseignant = enseignant;
    }

    @Override
    public String toString() {
        return "Classe [id=" + id + ", nom=" + nom + ", classe= " + enseignant +"]";
    }
}
