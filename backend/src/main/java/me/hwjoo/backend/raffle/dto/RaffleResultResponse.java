package me.hwjoo.backend.raffle.dto;

import static me.hwjoo.backend.common.util.JsonParser.extractPrizeAmount;
import static me.hwjoo.backend.common.util.JsonParser.extractPrizeName;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;
import me.hwjoo.backend.common.entity.Participation;
import me.hwjoo.backend.common.util.JsonParser;

// src/main/java/me/hwjoo/backend/raffle/dto/RaffleResultResponse.java
@Builder
public record RaffleResultResponse(
        Long eventId,
        UUID userUuid,
        ZonedDateTime participationTime,
        String resultStatus, // WIN/LOSE/PENDING
        String prizeName,
        Integer prizeAmount
) {
    public static RaffleResultResponse fromEntity(Participation participation) {
        return RaffleResultResponse.builder()
                .eventId(participation.getEvent().getId())
                .userUuid(participation.getUserUuid())
                .participationTime(participation.getParticipationTime())
                .resultStatus(extractResultStatus(participation))
                .prizeName(extractPrizeName(participation))
                .prizeAmount(extractPrizeAmount(participation))
                .build();
    }

    private static String extractResultStatus(Participation participation) {
        return participation.getResultData() != null ?
                JsonParser.parseStatus(participation.getResultData()) : "PENDING";
    }
}

