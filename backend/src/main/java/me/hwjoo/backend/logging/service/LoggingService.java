package me.hwjoo.backend.logging.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hwjoo.backend.logging.entity.EventLog;
import me.hwjoo.backend.logging.entity.EventLog.EventType;
import me.hwjoo.backend.logging.repository.EventLogRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

// src/main/java/me/hwjoo/backend/logging/service/LoggingService.java
@Slf4j
@Service
@RequiredArgsConstructor
public class LoggingService {
    private final EventLogRepository eventLogRepository;

    @Async("loggingTaskExecutor") // [수정] 전용 스레드 풀 사용
    public void logEvent(EventType eventType, UUID userUuid, Long targetId, Object details) {
        try {
            EventLog logEntry = EventLog.builder()
                    .timestamp(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                    .eventType(eventType)
                    .userUuid(userUuid)
                    .targetId(targetId)
                    .details(new ObjectMapper().writeValueAsString(details))
                    .build();

            eventLogRepository.save(logEntry);
        } catch (JsonProcessingException e) {
            log.error("이벤트 로깅 실패: {}", e.getMessage(), e);
        }
    }
}
