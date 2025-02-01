package me.hwjoo.backend.common.dto;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

// src/main/java/me/hwjoo/backend/common/dto/EventFilterRequest.java
@Getter
@Builder
public class EventFilterRequest {
    private String type;
    private ZonedDateTime fromDate;
    private ZonedDateTime toDate;

    public static EventFilterRequest of(String type, ZonedDateTime from, ZonedDateTime to) {
        return EventFilterRequest.builder()
                .type(type)
                .fromDate(from)
                .toDate(to)
                .build();
    }
}
