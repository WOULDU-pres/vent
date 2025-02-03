package me.hwjoo.backend.flashDeal.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

// src/main/java/me/hwjoo/backend/flashDeal/dto/FlashDealCreateRequest.java
@Getter
@Builder
public class FlashDealCreateRequest {
    @NotBlank(message = "상품명은 필수 입력값입니다")
    @Size(max = 100, message = "상품명은 100자 이내로 입력해주세요")
    private String productName;

    @Min(value = 1000, message = "원가격은 1000원 이상이어야 합니다")
    private Integer originalPrice;

    @Min(value = 100, message = "딜 가격은 100~999원 사이여야 합니다")
    @Max(value = 999)
    private Integer dealPrice;

    @Min(value = 1, message = "최소 1개 이상의 수량이 필요합니다")
    private Integer quantity;

    @Future(message = "시작 시간은 현재보다 이후여야 합니다")
    private ZonedDateTime startTime;

    @Builder
    public static FlashDealCreateRequest create(
            String productName,
            Integer originalPrice,
            Integer dealPrice,
            Integer quantity,
            ZonedDateTime startTime
    ) {
        return new FlashDealCreateRequest(
                productName, originalPrice, dealPrice, quantity, startTime
        );
    }
}
