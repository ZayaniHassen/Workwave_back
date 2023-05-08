package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormation")
    private Long idFormation;
    private String nomFormation;
    private String duree;

    private String instructeur;

    private String imageInstructeur;

    private String description;

    @OneToOne

    @JoinColumn(name = "idCateg")
    @JsonIgnore
    private Categorie Categ;
    @OneToOne(mappedBy = "formation")
    @JsonIgnore
    private Historique historique;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "formation")
    @JsonManagedReference
    private Set<Cours> cours;

    @OneToOne
    @JoinColumn(name = "quizz_id")
    @JsonIgnore
    private Quizz quizz;


    public Historique getHistorique() {
        return historique;
    }

    public void setHistorique(Historique historique) {
        this.historique = historique;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getInstructeur() {
        return instructeur;
    }

    public void setInstructeur(String instructeur) {
        this.instructeur = instructeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCateg() {
        return Categ;
    }

    public void setCateg(Categorie categ) {
        Categ = categ;
    }

    public String getImageInstructeur() {
        return imageInstructeur;
    }

    public void setImageInstructeur(String imageInstructeur) {
        this.imageInstructeur = imageInstructeur;
    }

    public Formation(Long idFormation, String nomFormation, String duree, String instructeur, String description, String imageInstructeur, Categorie categ) {
        this.idFormation = idFormation;
        this.nomFormation = nomFormation;
        this.duree = duree;
        this.instructeur = instructeur;
        this.description = description;
        this.imageInstructeur = imageInstructeur;

        Categ = categ;
    }

    public Formation() {
    }


}
