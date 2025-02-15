// src/main/java/me/hwjoo/backend/raffle/repository/RaffleEventRepository.java
package me.hwjoo.backend.raffle.repository;

import java.time.ZonedDateTime;
import java.util.List;
import me.hwjoo.backend.raffle.entity.RaffleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 추첨 이벤트 데이터 접근 계층
 * - JPA Repository 상속을 통한 기본 CRUD 제공
 * - 커스텀 쿼리 메서드로 복잡한 조회 기능 구현
 */
public interface RaffleEventRepository extends JpaRepository<RaffleEvent, Long> {

    /**
     * 현재 진행 가능한 활성화된 추첨 이벤트 조회
     * @param currentTime 기준 시간
     */
    @Query("SELECT re FROM RaffleEvent re " +
            "WHERE re.startTime <= :currentTime " +
            "AND re.endTime >= :currentTime " +
            "AND re.maxParticipants > (SELECT COUNT(p) FROM Participation p WHERE p.event.id = re.id)")
    List<RaffleEvent> findActiveEvents(@Param("currentTime") ZonedDateTime currentTime);
}
