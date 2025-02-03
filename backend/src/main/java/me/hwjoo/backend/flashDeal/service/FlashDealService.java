// src/main/java/me/hwjoo/backend/flashDeal/service/FlashDealService.java
package me.hwjoo.backend.flashDeal.service;

import jakarta.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.exception.AlreadyParticipatingException;
import me.hwjoo.backend.common.exception.InvalidDealStatusException;
import me.hwjoo.backend.common.exception.SoldOutException;
import me.hwjoo.backend.flashDeal.dto.FlashDealCreateRequest;
import me.hwjoo.backend.flashDeal.dto.FlashDealResponse;
import me.hwjoo.backend.flashDeal.entity.FlashDeal;
import me.hwjoo.backend.flashDeal.entity.FlashDeal.FlashDealStatus;
import me.hwjoo.backend.flashDeal.repository.FlashDealRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlashDealService {

    private final FlashDealRepository flashDealRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private static final String FLASH_DEAL_LOCK_KEY = "flash_deal:lock:";
    private static final long LOCK_DURATION = 10; // seconds

    @Transactional
    public FlashDealResponse participate(Long dealId, UUID userUuid) {
        String lockKey = FLASH_DEAL_LOCK_KEY + dealId + ":" + userUuid;

        // Redis 락 획득: 동시 접근을 방지하기 위해 setIfAbsent로 락을 획득합니다.
        Boolean acquired = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, "locked", LOCK_DURATION, TimeUnit.SECONDS);

        if (acquired == null || !acquired) {
            throw new AlreadyParticipatingException("이미 참여 중입니다.");
        }

        try {
            FlashDeal deal = flashDealRepository.findById(dealId)
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 딜입니다."));

            // 딜의 현재 상태가 ACTIVE인지 검증
            validateDealStatus(deal);
            // 남은 재고가 있는지 검증
            validateRemainingQuantity(deal);

            // 재고 차감 로직: FlashDeal 엔티티 내 decreaseQuantity() 메서드로 구현
            deal.decreaseQuantity();
            // 저장 후 DTO 매핑은 FlashDealResponse.fromEntity() 내에서 처리합니다.
            return FlashDealResponse.fromEntity(flashDealRepository.save(deal));
        } finally {
            // 락 해제: 항상 락을 해제하여 블로킹 상태가 지속되지 않도록 합니다.
            redisTemplate.delete(lockKey);
        }
    }

    /**
     * 새로운 Flash Deal 생성
     * - 딜 시작 시간이 현재 시간보다 미래인지 검증
     * - 딜 가격이 원가보다 낮은지 검증
     * - 초기 상태를 UPCOMING으로 설정
     *
     * @param request 생성 요청 DTO
     * @return 생성된 딜 정보
     */
    @Transactional
    public FlashDealResponse createDeal(FlashDealCreateRequest request) {
        validateDealPrice(request);
        validateStartTime(request.getStartTime());

        FlashDeal newDeal = FlashDeal.builder()
                .productName(request.getProductName())
                .originalPrice(request.getOriginalPrice())
                .dealPrice(request.getDealPrice())
                .quantity(request.getQuantity())
                .remainingQuantity(request.getQuantity())
                .startTime(request.getStartTime())
                .endTime(calculateEndTime(request.getStartTime()))
                .status(FlashDealStatus.UPCOMING)
                .build();

        return FlashDealResponse.fromEntity(flashDealRepository.save(newDeal));
    }

    private void validateDealPrice(FlashDealCreateRequest request) {
        if (request.getDealPrice() >= request.getOriginalPrice()) {
            throw new InvalidDealStatusException("딜 가격은 원가보다 낮아야 합니다");
        }
    }

    private void validateStartTime(ZonedDateTime startTime) {
        if (startTime.isBefore(ZonedDateTime.now())) {
            throw new InvalidDealStatusException("딜 시작 시간은 현재보다 이후여야 합니다");
        }
    }

    private ZonedDateTime calculateEndTime(ZonedDateTime startTime) {
        return startTime.plusHours(1); // 기본 1시간 딜 지속
    }

    /**
     * 딜의 상태가 ACTIVE인지 확인합니다.
     *
     * @param deal 검증 대상 FlashDeal 엔티티
     * @throws InvalidDealStatusException 딜이 ACTIVE가 아닌 경우 예외 발생
     */
    private void validateDealStatus(FlashDeal deal) {
        if (deal.getStatus() != FlashDealStatus.ACTIVE) {
            throw new InvalidDealStatusException("진행중인 딜이 아닙니다.");
        }
    }

    /**
     * 남은 재고가 있는지 검증합니다.
     *
     * @param deal 검증 대상 FlashDeal 엔티티
     * @throws SoldOutException 남은 재고가 0 이하인 경우 예외 발생
     */
    private void validateRemainingQuantity(FlashDeal deal) {
        if (deal.getRemainingQuantity() <= 0) {
            throw new SoldOutException("재고가 소진되었습니다.");
        }
    }
}





