package me.hwjoo.backend.common.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import me.hwjoo.backend.common.entity.Event;

// src/main/java/me/hwjoo/backend/common/dto/EventResponse.java
@Getter
@Builder
public class EventListResponse {
    private Long id;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer participantCount;

    public static EventListResponse fromEntity(Event event, Integer participantCount) {
        return EventListResponse.builder()
                .id(event.getId())
                .type(event.getType())
                .startTime(event.getStartTime().toLocalDateTime())
                .endTime(event.getEndTime().toLocalDateTime())
                .participantCount(participantCount)
                .build();
    }
}