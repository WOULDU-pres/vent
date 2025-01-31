package me.hwjoo.backend.common.repository;

import me.hwjoo.backend.common.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// src/main/java/me/hwjoo/backend/common/repository/ParticipationRepository.java
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT COUNT(p) FROM Participation p WHERE p.event.id = :eventId")
    Integer countByEventId(@Param("eventId") Long eventId);
}
