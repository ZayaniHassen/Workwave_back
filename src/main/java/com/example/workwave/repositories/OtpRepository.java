package com.example.workwave.repositories;

import com.example.workwave.entities.otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<otp,Long> {
    public Optional<otp> findByEmail(String email);
    List<otp> findByCreatedAtBefore(LocalDateTime createdAt);

}
