package me.hwjoo.backend.common.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.dto.EventListResponse;
import me.hwjoo.backend.common.repository.EventRepository;
import org.springframework.stereotype.Service;

// src/main/java/me/hwjoo/backend/common/service/EventService.java
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventListResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventListResponse::from)
                .collect(Collectors.toList());
    }
}
