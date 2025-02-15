package me.hwjoo.backend.raffle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.hwjoo.backend.common.entity.Event;

// src/main/java/me/hwjoo/backend/raffle/entity/RaffleEvent.java
@Getter
@Entity
@Table(name = "raffle_event")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RaffleEvent extends Event {

    @Column(nullable = false)
    private Integer maxParticipants;

    @Column(nullable = false)
    private ZonedDateTime resultAnnouncementTime;

    @Column(nullable = false)
    private Integer winnerCount;

    @Column(columnDefinition = "jsonb")
    private String dynamicFields; // 확장 필드 저장
}
