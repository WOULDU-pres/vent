// src/main/java/me/hwjoo/backend/raffle/repository/RaffleParticipationRepository.java
package me.hwjoo.backend.raffle.repository;

import java.util.UUID;
import me.hwjoo.backend.common.entity.Participation;
import me.hwjoo.backend.raffle.entity.RaffleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 추첨 참여 기록 관리 Repository
 * - 사용자 중복 참여 방지를 위한 조회 기능 포함
 */
public interface RaffleParticipationRepository extends JpaRepository<Participation, Long> {

    /**
     * 사용자의 특정 이벤트 중복 참여 확인
     * @param userUuid 사용자 UUID
     * @param event 추첨 이벤트
     */
    boolean existsByUserUuidAndEvent(UUID userUuid, RaffleEvent event);

    /**
     * 이벤트별 총 참여자 수 집계
     * @param eventId 추첨 이벤트 ID
     */
    long countByEventId(Long eventId);
}
