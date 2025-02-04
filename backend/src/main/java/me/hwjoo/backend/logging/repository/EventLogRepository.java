package me.hwjoo.backend.logging.repository;

import java.time.ZonedDateTime;
import me.hwjoo.backend.logging.entity.EventLog;
import me.hwjoo.backend.logging.entity.EventLog.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// src/main/java/me/hwjoo/backend/logging/repository/EventLogRepository.java
public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    @Query("SELECT el FROM EventLog el WHERE " +
            "(:eventType IS NULL OR el.eventType = :eventType) AND " +
            "(:startDate IS NULL OR el.timestamp >= :startDate) AND " +
            "(:endDate IS NULL OR el.timestamp <= :endDate)")
    Page<EventLog> searchLogs(
            @Param("eventType") EventType eventType,
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate,
            Pageable pageable
    );
}

