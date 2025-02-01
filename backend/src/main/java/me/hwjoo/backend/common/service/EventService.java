package me.hwjoo.backend.common.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.dto.EventListResponse;
import me.hwjoo.backend.common.repository.EventRepository;
import me.hwjoo.backend.common.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

// src/main/java/me/hwjoo/backend/common/service/EventService.java
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

//    // Event만 가져오기
//    public List<EventListResponse> getAllEvents() {
//        return eventRepository.findAll().stream()
//                .map(EventListResponse::from)
//                .collect(Collectors.toList());
//    }

    public List<EventListResponse> getAllEventsWithParticipants() {
        return eventRepository.findAll().stream()
                .map(event -> {
                    int count = participationRepository.countByEventId(event.getId());
                    return EventListResponse.fromEntity(event, count); // 수정된 부분
                })
                .collect(Collectors.toList());
    }
}
