package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
