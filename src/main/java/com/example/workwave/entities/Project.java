package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")

@Entity
@Table( name = "Project")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","User"})

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String projectname ;
    private String description ;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEmission;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateExpiration;
    private String etat;
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "project_user",
            joinColumns = { @JoinColumn(name = "projet_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "user_user_name", referencedColumnName = "userName") })
    private Set<User> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Project() {
    }

    public Project(Long id, String projectname, String description, Date dateEmission, Date dateExpiration, String etat) {
        this.id = id;
        this.projectname = projectname;
        this.description = description;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.etat = etat;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}