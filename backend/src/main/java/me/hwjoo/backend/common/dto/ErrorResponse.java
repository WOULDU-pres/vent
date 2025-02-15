package me.hwjoo.backend.common.dto;

import java.time.Instant;
import lombok.Builder;

// src/main/java/me/hwjoo/backend/common/dto/ErrorResponse.java

@Builder
public record ErrorResponse(
        String code,
        String title,
        String detail,
        Instant timestamp
) {
    public static ErrorResponse of(String code, String title, String detail) {
        return new ErrorResponse(code, title, detail, Instant.now());
    }
}
