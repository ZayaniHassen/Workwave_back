package com.example.workwave.repositories;

import com.example.workwave.entities.Role;
import com.example.workwave.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByEmail(String email);

    Page<User> findByRole(Role role, Pageable pageable);
    List<User> findByRole(Role role);

    public List<User> findByEtat (String etat);

    public User findByToken(String token);

    public boolean existsByEmail(String email);

}
