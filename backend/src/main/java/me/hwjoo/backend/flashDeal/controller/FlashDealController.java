package me.hwjoo.backend.flashDeal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.service.UserService;
import me.hwjoo.backend.flashDeal.dto.FlashDealCreateRequest;
import me.hwjoo.backend.flashDeal.dto.FlashDealResponse;
import me.hwjoo.backend.flashDeal.service.FlashDealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// src/main/java/me/hwjoo/backend/flashDeal/controller/FlashDealController.java
@Tag(name = "Flash Deal", description = "100원 딜 이벤트 관리 API")
@RestController
@RequestMapping("/api/flash-deals")
@RequiredArgsConstructor
public class FlashDealController {

    private final FlashDealService flashDealService;
    private final UserService userService;
    
    /**
     * Flash Deal 필터링 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<FlashDealResponse>> getDealsByStatus(
            @RequestParam(required = false) String status) {

        return ResponseEntity.ok(flashDealService.getDealsByStatus(status));
    }

    /**
     * 특가 딜 참여 API
     * @param dealId 대상 딜 ID (경로 변수)
     * @param userUuid 더미 사용자 UUID (헤더 값)
     * @return 참여 결과 DTO
     */
    @Operation(summary = "딜 참여", description = "사용자가 100원 딜에 참여하는 API")
    @ApiResponse(responseCode = "200", description = "참여 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/{dealId}/participate")
    public ResponseEntity<FlashDealResponse> participateDeal(
            @PathVariable Long dealId) {

        FlashDealResponse response = flashDealService.participate(dealId,
                userService.getDummyUserUuid());
        return ResponseEntity.ok(response);
    }

    /**
     * 특가 딜 생성 API
     * @param request 생성 요청 DTO
     * @return 생성 결과 DTO
     */
    @Operation(summary = "딜 생성", description = "새로운 100원 딜 생성 API")
    @ApiResponse(responseCode = "201", description = "생성 성공")
    @PostMapping
    public ResponseEntity<FlashDealResponse> createDeal(
            @Valid @RequestBody FlashDealCreateRequest request) {

        FlashDealResponse response = flashDealService.createDeal(request);
        URI location = URI.create("/api/flash-deals/" + response.getId());
        return ResponseEntity.created(location).body(response);
    }
}
