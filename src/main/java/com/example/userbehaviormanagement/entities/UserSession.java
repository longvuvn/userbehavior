package com.example.userbehaviormanagement.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"user_session\"")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ipAddress;
    private String browserInfo;
    private String osInfo;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private Set<EventLog> eventLogs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
