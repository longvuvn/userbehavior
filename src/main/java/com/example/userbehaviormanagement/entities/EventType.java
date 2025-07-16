package com.example.userbehaviormanagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"event_type\"")
@Data
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "eventType", fetch = FetchType.LAZY)
    private Set<EventLog> eventLogs;

    @OneToMany(mappedBy = "eventType", fetch = FetchType.LAZY)
    private Set<AnalysisResult> analysisResults;
}
