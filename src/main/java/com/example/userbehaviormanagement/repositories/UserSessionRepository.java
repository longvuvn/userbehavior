package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSessionRepository extends JpaRepository<UserSession, UUID> {
}
