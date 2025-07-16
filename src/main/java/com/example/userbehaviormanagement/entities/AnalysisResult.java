package com.example.userbehaviormanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "analysis_result")
@EntityListeners(AuditingEntityListener.class)
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;

    @Column(name = "total_events")
    private Integer totalEvents;

    @Column(name = "unique_sessions")
    private Integer uniqueSessions;

    @Column(name = "unique_users")
    private Integer uniqueUsers;

    @Column(name = "interaction_rate")
    private Double interactionRate;

    @Column(name = "most_common_element")
    private String mostCommonElement;

    @Column(name = "avg_x")
    private Integer avgX;

    @Column(name = "avg_y")
    private Integer avgY;

    @LastModifiedDate
    @Column(name = "last_updated")
    private Instant lastUpdated;
}
