package me.hwjoo.backend.flashDeal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hwjoo.backend.common.exception.SoldOutException;

// src/main/java/me/hwjoo/backend/flashDeal/entity/FlashDeal.java
@Getter
@Entity
@Table(name = "flash_deal")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FlashDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer originalPrice;

    @Column(nullable = false)
    private Integer dealPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer remainingQuantity;

    @Column(nullable = false)
    private ZonedDateTime startTime;

    @Column(nullable = false)
    private ZonedDateTime endTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlashDealStatus status;

    public enum FlashDealStatus {
        UPCOMING, ACTIVE, ENDED
    }

    /**
     * 재고 수량 감소 메서드 (낙관적 락 버전 관리 병행)
     * @param quantity 감소시킬 수량 (기본값 1)
     * @return 변경된 재고 수량
     * @throws SoldOutException 재고 부족 시 발생
     */
    public int decreaseQuantity(int quantity) {
        if (this.remainingQuantity < quantity) {
            throw new SoldOutException("재고가 부족합니다");
        }
        this.remainingQuantity -= quantity;
        return this.remainingQuantity;
    }

    /**
     * 1개 단위 재고 감소 (기본 동작)
     */
    public void decreaseQuantity() {
        decreaseQuantity(1);
    }
}
