package me.hwjoo.backend.raffle.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;
import me.hwjoo.backend.common.entity.Participation;

// src/main/java/me/hwjoo/backend/raffle/dto/RaffleParticipationResponse.java
@Builder
public record RaffleParticipationResponse(
        Long participationId,
        UUID userUuid,
        Long eventId,
        ZonedDateTime participationTime,
        String status // PENDING, WIN, LOSE
) {
    public static RaffleParticipationResponse fromEntity(Participation participation) {
        return new RaffleParticipationResponse(
                participation.getId(),
                participation.getUserUuid(),
                participation.getEvent().getId(),
                participation.getParticipationTime(),
                "PENDING"
        );
    }
}
