package me.hwjoo.backend.common.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.dto.EventListResponse;
import me.hwjoo.backend.common.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// src/main/java/me/hwjoo/backend/common/controller/EventController.java
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventListResponse>> getEventList() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
}