package me.hwjoo.backend.logging.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// src/main/java/me/hwjoo/backend/logging/entity/EventLog.java
@Entity
@Table(name = "event_log")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime timestamp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private UUID userUuid;  // 이벤트 주체 사용자 (nullable)
    private Long targetId; // 연관된 리소스 ID (예: 딜 ID)

    @Column(columnDefinition = "jsonb") // PostgreSQL JSONB 타입 활용
    private String details;

    public enum EventType {
        DEAL_CREATED, DEAL_PARTICIPATED, DEAL_STATUS_CHANGED, ERROR_OCCURRED
    }
}

