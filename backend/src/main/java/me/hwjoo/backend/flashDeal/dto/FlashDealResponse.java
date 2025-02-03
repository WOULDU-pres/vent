package me.hwjoo.backend.flashDeal.dto;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import me.hwjoo.backend.flashDeal.entity.FlashDeal;

// src/main/java/me/hwjoo/backend/flashDeal/dto/FlashDealResponse.java
@Getter
@Builder
public class FlashDealResponse {
    private Long id;
    private String productName;
    private Integer originalPrice;
    private Integer dealPrice;
    private Integer quantity;
    private Integer remainingQuantity;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String status;

    // FlashDeal 엔티티로부터 DTO를 생성하는 메서드
    public static FlashDealResponse fromEntity(FlashDeal flashDeal) {
        return FlashDealResponse.builder()
                .id(flashDeal.getId())
                .productName(flashDeal.getProductName())
                .originalPrice(flashDeal.getOriginalPrice())
                .dealPrice(flashDeal.getDealPrice())
                .quantity(flashDeal.getQuantity())
                .remainingQuantity(flashDeal.getRemainingQuantity())
                .startTime(flashDeal.getStartTime())
                .endTime(flashDeal.getEndTime())
                .status(flashDeal.getStatus().name())
                .build();
    }
}
