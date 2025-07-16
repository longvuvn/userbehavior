package com.example.userbehaviormanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userbehaviormanagement.entities.Role;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
