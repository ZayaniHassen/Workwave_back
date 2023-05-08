package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Entity
@Table( name = "task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String taskName;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date datedebut;


    @Temporal(TemporalType.DATE)
    private Date datefin;

    private String etat;


    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "task_user",
            joinColumns = { @JoinColumn(name = "taskId", referencedColumnName = "taskId")},
            inverseJoinColumns = { @JoinColumn(name = "userName", referencedColumnName = "userName") })
    private Set<User> user;

    @ManyToOne
    @JoinColumn(name = "scrumboard_id")
    @JsonBackReference
    private ScrumBoard scrumboard;
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }


    public ScrumBoard getScrumboard() {
        return scrumboard;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setScrumboard(ScrumBoard scrumboard) {
        this.scrumboard = scrumboard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Task(Long taskId, String taskName, String description, Date datedebut, Date datefin, String etat, Set<User> user, ScrumBoard scrumboard) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.etat = etat;
        this.user = user;
        this.scrumboard = scrumboard;
    }

    public Task() {
    }
}


