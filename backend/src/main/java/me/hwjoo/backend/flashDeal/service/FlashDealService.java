// src/main/java/me/hwjoo/backend/flashDeal/service/FlashDealService.java
package me.hwjoo.backend.flashDeal.service;

import jakarta.persistence.EntityNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hwjoo.backend.common.exception.AlreadyParticipatingException;
import me.hwjoo.backend.common.exception.InvalidDealStatusException;
import me.hwjoo.backend.common.exception.SoldOutException;
import me.hwjoo.backend.flashDeal.dto.FlashDealCreateRequest;
import me.hwjoo.backend.flashDeal.dto.FlashDealResponse;
import me.hwjoo.backend.flashDeal.entity.FlashDeal;
import me.hwjoo.backend.flashDeal.entity.FlashDeal.FlashDealStatus;
import me.hwjoo.backend.flashDeal.repository.FlashDealRepository;
import me.hwjoo.backend.logging.service.LoggingService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlashDealService {

    private final FlashDealRepository flashDealRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final LoggingService loggingService;

    private static final String FLASH_DEAL_LOCK_KEY = "flash_deal:lock:";
    private static final long LOCK_DURATION = 10; // seconds

    @Transactional(readOnly = true)
    public List<FlashDealResponse> getAllDeals() {
        return flashDealRepository.findAll().stream()
                .map(FlashDealResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FlashDealResponse> getDealsByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAllDeals();
        }

        FlashDealStatus dealStatus = parseStatus(status);
        return flashDealRepository.findByStatus(dealStatus).stream()
                .map(FlashDealResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private FlashDealStatus parseStatus(String status) {
        try {
            return FlashDealStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidDealStatusException("유효하지 않은 상태값: " + status);
        }
    }

    @Transactional
    public FlashDealResponse participate(Long dealId, UUID userUuid) {
        String lockKey = FLASH_DEAL_LOCK_KEY + dealId;
        System.out.println(lockKey);

        // Redis 락 획득: 동시 접근을 방지하기 위해 setIfAbsent로 락을 획득합니다.
        Boolean acquired = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, "locked", LOCK_DURATION, TimeUnit.SECONDS);
        System.out.println("Redis 락 획득: 동시 접근을 방지하기 위해 setIfAbsent로 락을 획득합니다.");

        if (acquired == null || !acquired) {
            throw new AlreadyParticipatingException("이미 참여 중입니다.");
        }

        try {
            FlashDeal deal = flashDealRepository.findByIdWithLock(dealId)
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 딜입니다."));

            // 딜의 현재 상태가 ACTIVE인지 검증
            validateDealStatus(deal);
            System.out.println("validateDealStatus(deal);");

            // 남은 재고가 있는지 검증
            validateRemainingQuantity(deal);
            System.out.println("validateRemainingQuantity(deal);");

            // 재고 차감 로직: FlashDeal 엔티티 내 decreaseQuantity() 메서드로 구현
            deal.decreaseQuantity();
            System.out.println("재고 차감 1개");
            
            // 저장 후 DTO 매핑은 FlashDealResponse.fromEntity() 내에서 처리합니다.
            return FlashDealResponse.fromEntity(flashDealRepository.save(deal));
        } finally {
            // 락 해제: 항상 락을 해제하여 블로킹 상태가 지속되지 않도록 합니다.
            redisTemplate.delete(lockKey);
            System.out.println("락 해제: 항상 락을 해제하여 블로킹 상태가 지속되지 않도록 합니다.");
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

        // 차후 로깅 추가 예정
//        loggingService.logEvent(
//                EventType.DEAL_CREATED,
//                getCurrentUserUuid(), // 사용자 인증 시스템 연동 필요
//                created.getId(),
//                Map.of(
//                        "originalPrice", request.getOriginalPrice(),
//                        "dealPrice", request.getDealPrice()
//                )
//        );

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
        if (deal.getStatus() == FlashDealStatus.ENDED) {
            throw new InvalidDealStatusException("이미 종료된 딜입니다");
        }
        // 스케줄러와의 상태 불일치 방지
        if (deal.getStatus() == FlashDealStatus.UPCOMING &&
                deal.getStartTime().isBefore(ZonedDateTime.now())) {
            throw new IllegalStateException("업데이트 지연 발생. 잠시 후 다시 시도해주세요");
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

    /**
     * 특가 이벤트 상태 관리 스케쥴러
     */
    @Scheduled(fixedRate = 60_000) // 1분 간격 실행
    @Transactional
    public void autoUpdateDealStatus() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int activatedCount = flashDealRepository.activateExpiredDeals(now);
        int completedCount = flashDealRepository.completeExpiredDeals(now);

        log.info("자동 상태 업데이트: 활성화 {}건, 종료 {}건",
                activatedCount, completedCount);
    }
}





