package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventLogRepository extends JpaRepository<EventLog, UUID> {


}
