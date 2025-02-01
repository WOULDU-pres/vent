package me.hwjoo.backend.common.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.dto.EventResponse;
import me.hwjoo.backend.common.repository.EventRepository;
import me.hwjoo.backend.common.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

// src/main/java/me/hwjoo/backend/common/service/EventService.java
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    public List<EventResponse> getAllEventsWithParticipants() {
        return eventRepository.findAll().stream()
                .map(event -> {
                    int count = participationRepository.countByEventId(event.getId());
                    return EventResponse.fromEntity(event, count); // 수정된 부분
                })
                .collect(Collectors.toList());
    }

    public List<EventResponse> getFilteredEvents(String type) {
        return eventRepository.findAllByType(type).stream()
                .map(event -> EventResponse.fromEntity(event, participationRepository.countByEventId(event.getId())))
                .collect(Collectors.toList());
    }
}
