package com.example.userbehaviormanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "\"event_log\"")
@Data
@EntityListeners(AuditingEntityListener.class)
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private UserSession session;

    @ManyToOne
    @JsonProperty("event_type_id")
    private EventType eventType;

    private String url;

    private Integer x;
    private Integer y;

    @Column(name = "event_data", columnDefinition = "TEXT")
    private String eventData;

    @CreatedDate
    @Column(name = "timestamp", updatable = false)
    private Instant timestamp;
}
