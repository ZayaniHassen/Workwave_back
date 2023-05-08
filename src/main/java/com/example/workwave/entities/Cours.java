package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Cours {

    @ManyToOne
    @JsonBackReference
    Formation formation;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCours")
    private Long idCours;
    private String titre;
    private String image1;

    private String SousTitre1;

    private String Contenu1;

    private String SousTitre2;

    private String Contenu2;

    private String SousTitre3;

    private String Contenu3;
    private String SousTitre4;

    private String Contenu4;

    public Cours() {
    }

    public Long getIdCours() {
        return idCours;
    }

    public void setIdCours(Long idCours) {
        this.idCours = idCours;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre1() {
        return SousTitre1;
    }

    public void setSousTitre1(String sousTitre1) {
        SousTitre1 = sousTitre1;
    }

    public String getContenu1() {
        return Contenu1;
    }

    public void setContenu1(String contenu1) {
        Contenu1 = contenu1;
    }

    public String getSousTitre2() {
        return SousTitre2;
    }

    public void setSousTitre2(String sousTitre2) {
        SousTitre2 = sousTitre2;
    }

    public String getContenu2() {
        return Contenu2;
    }

    public void setContenu2(String contenu2) {
        Contenu2 = contenu2;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public String getSousTitre3() {
        return SousTitre3;
    }

    public void setSousTitre3(String sousTitre3) {
        SousTitre3 = sousTitre3;
    }

    public String getContenu3() {
        return Contenu3;
    }

    public void setContenu3(String contenu3) {
        Contenu3 = contenu3;
    }

    public String getSousTitre4() {
        return SousTitre4;
    }

    public void setSousTitre4(String sousTitre4) {
        SousTitre4 = sousTitre4;
    }

    public String getContenu4() {
        return Contenu4;
    }

    public void setContenu4(String contenu4) {
        Contenu4 = contenu4;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

}
