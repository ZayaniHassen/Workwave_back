package com.example.workwave.repositories;

import com.example.workwave.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    public Role findRoleByRoleName(String roleName);

}
