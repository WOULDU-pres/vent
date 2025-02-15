package me.hwjoo.backend.common.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import me.hwjoo.backend.common.entity.Event;
import me.hwjoo.backend.common.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// src/main/java/me/hwjoo/backend/common/repository/ParticipationRepository.java
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT COUNT(p) FROM Participation p WHERE p.event.id = :eventId")
    Integer countByEventId(@Param("eventId") Long eventId);

    List<Participation> findByEventId(Long eventId);

    Boolean existsByUserUuidAndEvent(UUID userUuid, Event event);

    /**
     * 이벤트 ID와 사용자 UUID로 참여 기록 조회
     * - 복합 조건 검색을 위한 커스텀 JPQL 쿼리
     * - N+1 문제 방지를 위해 FETCH JOIN 적용
     */
    @Query("SELECT p FROM Participation p " +
            "JOIN FETCH p.event e " +
            "WHERE e.id = :eventId AND p.userUuid = :userUuid")
    Optional<Participation> findByEventIdAndUserUuid(
            @Param("eventId") Long eventId,
            @Param("userUuid") UUID userUuid
    );
}
