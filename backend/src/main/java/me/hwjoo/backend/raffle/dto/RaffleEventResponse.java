// src/main/java/me/hwjoo/backend/raffle/dto/RaffleEventResponse.java
package me.hwjoo.backend.raffle.dto;

import lombok.Builder;
import me.hwjoo.backend.raffle.entity.RaffleEvent;

import java.time.ZonedDateTime;

@Builder
public record RaffleEventResponse(
        Long id,
        String type,
        ZonedDateTime startTime,
        ZonedDateTime endTime,
        Integer maxParticipants,
        ZonedDateTime resultAnnouncementTime,
        Integer winnerCount
) {
    public static RaffleEventResponse fromEntity(RaffleEvent event) {
        return RaffleEventResponse.builder()
                .id(event.getId())
                .type(event.getType())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .maxParticipants(event.getMaxParticipants())
                .resultAnnouncementTime(event.getResultAnnouncementTime())
                .winnerCount(event.getWinnerCount())
                .build();
    }
}
