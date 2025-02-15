// src/main/java/me/hwjoo/backend/raffle/controller/RaffleEventController.java
package me.hwjoo.backend.raffle.controller;

import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.raffle.dto.*;
import me.hwjoo.backend.raffle.service.RaffleEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/raffles")
public class RaffleEventController {

    private final RaffleEventService raffleEventService;

    /**
     * 활성화된 추첨 이벤트 목록 조회
     */
    @GetMapping("/active")
    public ResponseEntity<List<RaffleEventResponse>> getActiveRaffles() {
        return ResponseEntity.ok(raffleEventService.getActiveEvents());
    }

    /**
     * 추첨 이벤트 참여 요청 처리
     */
    @PostMapping("/participate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RaffleParticipationResponse participateRaffle(
            @Valid @RequestBody RaffleParticipationRequest request
    ) {
        return raffleEventService.participate(request);
    }

    /**
     * 추첨 결과 조회
     */
    @GetMapping("/{eventId}/result")
    public ResponseEntity<RaffleResultResponse> getRaffleResult(
            @PathVariable Long eventId,
            @RequestParam UUID userUuid
    ) {
        return ResponseEntity.ok(
                raffleEventService.getParticipationResult(eventId, userUuid)
        );
    }
}
