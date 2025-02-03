package me.hwjoo.backend.flashDeal.repository;

import java.time.ZonedDateTime;
import java.util.List;
import me.hwjoo.backend.flashDeal.entity.FlashDeal;
import me.hwjoo.backend.flashDeal.entity.FlashDeal.FlashDealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// src/main/java/me/hwjoo/backend/flashDeal/repository/FlashDealRepository.java
@Repository
public interface FlashDealRepository extends JpaRepository<FlashDeal, Long> {
    List<FlashDeal> findByStatus(FlashDealStatus status);
    List<FlashDeal> findByStartTimeBetween(ZonedDateTime start, ZonedDateTime end);
}

