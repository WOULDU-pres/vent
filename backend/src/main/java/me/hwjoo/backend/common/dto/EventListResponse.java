package me.hwjoo.backend.common.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import me.hwjoo.backend.common.entity.Event;

// src/main/java/me/hwjoo/backend/common/dto/EventResponse.java
@Getter
@Builder
public class EventListResponse {
    private Long id;
    private String eventType;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static EventListResponse from(Event event) {
        ZonedDateTime now = ZonedDateTime.now();
        String status = event.getStartTime().isAfter(now) ? "예정" :
                event.getEndTime().isBefore(now) ? "종료" : "진행중";

        return EventListResponse.builder()
                .id(event.getId())
                .eventType(event.getType())
                .status(status)
                .startTime(event.getStartTime().toLocalDateTime())
                .endTime(event.getEndTime().toLocalDateTime())
                .build();
    }
}