package com.example.workwave.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Quizz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuizz")
    private Long idQuizz;

    private String Question1;
    private String Question2;
    private String Question3;
    private String Question4;
    private String rep1Q1;
    private String rep2Q1;
    private String rep3Q1;
    private String repCorrectQ1;
    private String rep1Q2;
    private String rep2Q2;
    private String rep3Q2;
    private String repCorrectQ2;
    private String rep1Q3;
    private String rep2Q3;
    private String rep3Q3;
    private String repCorrectQ3;
    private String rep1Q4;
    private String rep2Q4;
    private String rep3Q4;
    private String repCorrectQ4;
    @OneToOne(mappedBy = "quizz")

    private Formation formation;

    public Quizz() {
    }

    public Long getIdQuizz() {
        return idQuizz;
    }

    public void setIdQuizz(Long idQuizz) {
        this.idQuizz = idQuizz;
    }

    public String getQuestion1() {
        return Question1;
    }

    public void setQuestion1(String question1) {
        Question1 = question1;
    }

    public String getQuestion2() {
        return Question2;
    }

    public void setQuestion2(String question2) {
        Question2 = question2;
    }

    public String getQuestion3() {
        return Question3;
    }

    public void setQuestion3(String question3) {
        Question3 = question3;
    }

    public String getQuestion4() {
        return Question4;
    }

    public void setQuestion4(String question4) {
        Question4 = question4;
    }

    public String getRep1Q1() {
        return rep1Q1;
    }

    public void setRep1Q1(String rep1Q1) {
        this.rep1Q1 = rep1Q1;
    }

    public String getRep2Q1() {
        return rep2Q1;
    }

    public void setRep2Q1(String rep2Q1) {
        this.rep2Q1 = rep2Q1;
    }

    public String getRep3Q1() {
        return rep3Q1;
    }

    public void setRep3Q1(String rep3Q1) {
        this.rep3Q1 = rep3Q1;
    }

    public String getRepCorrectQ1() {
        return repCorrectQ1;
    }

    public void setRepCorrectQ1(String repCorrectQ1) {
        this.repCorrectQ1 = repCorrectQ1;
    }

    public String getRep1Q2() {
        return rep1Q2;
    }

    public void setRep1Q2(String rep1Q2) {
        this.rep1Q2 = rep1Q2;
    }

    public String getRep2Q2() {
        return rep2Q2;
    }

    public void setRep2Q2(String rep2Q2) {
        this.rep2Q2 = rep2Q2;
    }

    public String getRep3Q2() {
        return rep3Q2;
    }

    public void setRep3Q2(String rep3Q2) {
        this.rep3Q2 = rep3Q2;
    }

    public String getRepCorrectQ2() {
        return repCorrectQ2;
    }

    public void setRepCorrectQ2(String repCorrectQ2) {
        this.repCorrectQ2 = repCorrectQ2;
    }

    public String getRep1Q3() {
        return rep1Q3;
    }

    public void setRep1Q3(String rep1Q3) {
        this.rep1Q3 = rep1Q3;
    }

    public String getRep2Q3() {
        return rep2Q3;
    }

    public void setRep2Q3(String rep2Q3) {
        this.rep2Q3 = rep2Q3;
    }

    public String getRep3Q3() {
        return rep3Q3;
    }

    public void setRep3Q3(String rep3Q3) {
        this.rep3Q3 = rep3Q3;
    }

    public String getRepCorrectQ3() {
        return repCorrectQ3;
    }

    public void setRepCorrectQ3(String repCorrectQ3) {
        this.repCorrectQ3 = repCorrectQ3;
    }

    public String getRep1Q4() {
        return rep1Q4;
    }

    public void setRep1Q4(String rep1Q4) {
        this.rep1Q4 = rep1Q4;
    }

    public String getRep2Q4() {
        return rep2Q4;
    }

    public void setRep2Q4(String rep2Q4) {
        this.rep2Q4 = rep2Q4;
    }

    public String getRep3Q4() {
        return rep3Q4;
    }

    public void setRep3Q4(String rep3Q4) {
        this.rep3Q4 = rep3Q4;
    }

    public String getRepCorrectQ4() {
        return repCorrectQ4;
    }

    public void setRepCorrectQ4(String repCorrectQ4) {
        this.repCorrectQ4 = repCorrectQ4;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }


}
