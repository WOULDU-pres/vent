package me.hwjoo.backend.flashDeal.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.flashDeal.dto.FlashDealCreateRequest;
import me.hwjoo.backend.flashDeal.dto.FlashDealResponse;
import me.hwjoo.backend.flashDeal.service.FlashDealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// src/main/java/me/hwjoo/backend/flashDeal/controller/FlashDealController.java
@RestController
@RequestMapping("/api/flash-deals")
@RequiredArgsConstructor
public class FlashDealController {

    private final FlashDealService flashDealService;

    /**
     * 특가 딜 참여 API
     * @param dealId 대상 딜 ID (경로 변수)
     * @param userUuid 사용자 UUID (헤더 값)
     * @return 참여 결과 DTO
     */
    @PostMapping("/{dealId}/participate")
    public ResponseEntity<FlashDealResponse> participateDeal(
            @PathVariable Long dealId,
            @RequestHeader("X-User-UUID") UUID userUuid) {

        FlashDealResponse response = flashDealService.participate(dealId, userUuid);
        return ResponseEntity.ok(response);
    }

    /**
     * 특가 딜 생성 API
     * @param request 생성 요청 DTO
     * @return 생성 결과 DTO
     */
    @PostMapping
    public ResponseEntity<FlashDealResponse> createDeal(
            @Valid @RequestBody FlashDealCreateRequest request) {

        FlashDealResponse response = flashDealService.createDeal(request);
        URI location = URI.create("/api/flash-deals/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }
}
