package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
}
