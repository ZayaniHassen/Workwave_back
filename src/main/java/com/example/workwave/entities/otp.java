package com.example.workwave.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Entity

public class otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String email;
    private int otp;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public otp() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public otp(String email, int otp) {
        this.email = email;
        this.otp = otp;
    }
}
