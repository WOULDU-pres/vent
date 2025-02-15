package me.hwjoo.backend.raffle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import me.hwjoo.backend.common.entity.Event;
import me.hwjoo.backend.common.entity.Participation;

// src/main/java/me/hwjoo/backend/raffle/dto/RaffleParticipationRequest.java
@Getter
public class RaffleParticipationRequest {

    @NotBlank
    private UUID userUuid;

    @NotNull
    private Long raffleEventId;


    public RaffleParticipationRequest(UUID userUuid, Long raffleEventId) {
        this.userUuid = userUuid;
        this.raffleEventId = raffleEventId;
    }

    // ModelMapper 사용을 위한 변환 메서드
    public Participation toEntity() {
        return Participation.builder()
                .userUuid(this.userUuid)
                .event(Event.builder().id(this.raffleEventId).build())
                .build();
    }
}
