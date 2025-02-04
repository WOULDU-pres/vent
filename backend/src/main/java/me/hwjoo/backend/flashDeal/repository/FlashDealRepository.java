package me.hwjoo.backend.flashDeal.repository;

import jakarta.persistence.LockModeType;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import me.hwjoo.backend.flashDeal.entity.FlashDeal;
import me.hwjoo.backend.flashDeal.entity.FlashDeal.FlashDealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// src/main/java/me/hwjoo/backend/flashDeal/repository/FlashDealRepository.java
@Repository
public interface FlashDealRepository extends JpaRepository<FlashDeal, Long> {
    List<FlashDeal> findByStatus(FlashDealStatus status);
    List<FlashDeal> findByStartTimeBetween(ZonedDateTime start, ZonedDateTime end);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT fd FROM FlashDeal fd WHERE fd.id = :id")
    Optional<FlashDeal> findByIdWithLock(@Param("id") Long id);

    @Modifying
    @Query("UPDATE FlashDeal fd SET fd.status = 'ACTIVE' " +
            "WHERE fd.status = 'UPCOMING' AND fd.startTime <= :now")
    int activateExpiredDeals(@Param("now") ZonedDateTime now);

    @Modifying
    @Query("UPDATE FlashDeal fd SET fd.status = 'ENDED' " +
            "WHERE fd.status = 'ACTIVE' AND fd.endTime <= :now")
    int completeExpiredDeals(@Param("now") ZonedDateTime now);
}

