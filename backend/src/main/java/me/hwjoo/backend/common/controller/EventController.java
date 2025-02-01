package me.hwjoo.backend.common.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.dto.EventResponse;
import me.hwjoo.backend.common.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// src/main/java/me/hwjoo/backend/common/controller/EventController.java
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // 이벤트 리스트 전체 가져오기 API
    @GetMapping
    public ResponseEntity<List<EventResponse>> getEventList() {
        return ResponseEntity.ok(eventService.getAllEventsWithParticipants());
    }

    // 이벤트 필터링(검색) API
    @GetMapping("/filter")
    public ResponseEntity<List<EventResponse>> getFilteredEvents(
            @RequestParam(required = false) String type
    ) {
        return ResponseEntity.ok(eventService.getFilteredEvents(type));
    }

}