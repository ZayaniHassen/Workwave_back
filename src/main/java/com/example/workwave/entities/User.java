package com.example.workwave.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userName")

@Entity
public class User {
    @Id
    @Column(unique = true)
    private String userName;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String fileName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int phoneNumber;
    @Column(name = "token")
    private String token;
    private boolean ban;
    private boolean tfa;
    private String etat;

    @OneToOne(mappedBy="user")
    private Historique historique;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<holiday> holidays = new ArrayList<>();
    @ManyToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projet;

    @ManyToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")})
    private Set<Role> role;

    public User() {

    }


    public <E> User(String userName, String password, ArrayList<E> es) {

    }

    public User(String userName, String nom, String prenom, String password, String email, String fileName, Gender gender, int phoneNumber, Set<Role> role) {
        this.userName = userName;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.fileName = fileName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Historique getHistorique() {
        return historique;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setHistorique(Historique historique) {
        this.historique = historique;
    }

    public List<holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<holiday> holidays) {
        this.holidays = holidays;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String Token) {
        this.token = Token;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Set<Project> getProjet() {
        return projet;
    }

    public void setProjet(Set<Project> projet) {
        this.projet = projet;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public boolean isTfa() {
        return tfa;
    }

    public void setTfa(boolean tfa) {
        this.tfa = tfa;
    }

}
