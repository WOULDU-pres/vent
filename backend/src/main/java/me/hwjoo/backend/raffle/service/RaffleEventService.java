// src/main/java/me/hwjoo/backend/raffle/service/RaffleEventService.java
package me.hwjoo.backend.raffle.service;


import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.hwjoo.backend.common.entity.Participation;
import me.hwjoo.backend.common.exception.AlreadyParticipatingException;
import me.hwjoo.backend.common.exception.ConcurrentParticipationException;
import me.hwjoo.backend.common.exception.TooManyRequestsException;
import me.hwjoo.backend.common.repository.ParticipationRepository;
import me.hwjoo.backend.raffle.dto.RaffleEventResponse;
import me.hwjoo.backend.raffle.dto.RaffleParticipationRequest;
import me.hwjoo.backend.raffle.dto.RaffleParticipationResponse;
import me.hwjoo.backend.raffle.dto.RaffleResultResponse;
import me.hwjoo.backend.raffle.entity.RaffleEvent;
import me.hwjoo.backend.raffle.exception.EventExpiredException;
import me.hwjoo.backend.raffle.exception.EventNotFoundException;
import me.hwjoo.backend.raffle.exception.MaxParticipantsExceededException;
import me.hwjoo.backend.raffle.exception.ParticipationNotFoundException;
import me.hwjoo.backend.raffle.exception.RaffleProcessException;
import me.hwjoo.backend.raffle.exception.ResultNotAvailableException;
import me.hwjoo.backend.raffle.repository.RaffleEventRepository;
import org.redisson.api.RLock;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 추첨 이벤트 핵심 비즈니스 로직 처리 서비스
 * - 이벤트 조회, 유효성 검증, 참여자 관리 기능 제공
 */
@Service
@RequiredArgsConstructor
public class RaffleEventService {

    private final RedissonClient redissonClient;
    private final RRateLimiter userRateLimiter;
    private final RaffleEventRepository raffleEventRepository;
    private final ParticipationRepository participationRepository;

    /**
     * 현재 활성화된 추첨 이벤트 조회
     */
    @Transactional(readOnly = true)
    public List<RaffleEventResponse> getActiveEvents() {
        List<RaffleEvent> activeEvents = raffleEventRepository.findActiveEvents(ZonedDateTime.now());
        return activeEvents.stream()
                .map(RaffleEventResponse::fromEntity)
                .toList();
    }

    /**
     * 이벤트 상세 정보 조회
     * @param eventId 추첨 이벤트 ID
     */
    @Transactional(readOnly = true)
    public RaffleEventResponse getEventDetails(Long eventId) {
        return raffleEventRepository.findById(eventId)
                .map(RaffleEventResponse::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
    }

    @Transactional
    public RaffleParticipationResponse participate(RaffleParticipationRequest request) {
        // 1. Rate Limiter 체크 (분당 10회 제한)
        if (!userRateLimiter.tryAcquire()) {
            throw new TooManyRequestsException("분당 10회 요청 제한 초과");
        }

        // 2. 분산 락 처리
        String lockKey = "raffle_lock:" + request.getRaffleEventId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean isLocked = lock.tryLock(5, 30, TimeUnit.SECONDS);
            if (!isLocked) throw new ConcurrentParticipationException("동시 접속 처리중");

            RaffleEvent event = raffleEventRepository.findById(request.getRaffleEventId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));

            validateParticipation(event, request.getUserUuid());

            Participation participation = participationRepository.save(request.toEntity());
            return RaffleParticipationResponse.fromEntity(participation);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RaffleProcessException("시스템 오류 발생");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public List<UUID> drawWinners(RaffleEvent event) {
        List<Participation> participants = participationRepository.findByEventId(event.getId());
        Collections.shuffle(participants); // Fisher-Yates 셔플

        return participants.stream()
                .limit(event.getWinnerCount())
                .map(Participation::getUserUuid)
                .collect(Collectors.toList());
    }

    private void validateParticipation(RaffleEvent event, UUID userUuid) {
        // 1. 이벤트 시간 유효성 검사
        if (ZonedDateTime.now().isAfter(event.getEndTime())) {
            throw new EventExpiredException("종료된 이벤트");
        }

        // 2. 중복 참여 검증
        if (participationRepository.existsByUserUuidAndEvent(userUuid, event)) {
            throw new AlreadyParticipatingException("이미 참여");
        }

        // 3. 최대 인원 제한 검사
        long currentParticipants = participationRepository.countByEventId(event.getId());
        if (currentParticipants >= event.getMaxParticipants()) {
            throw new MaxParticipantsExceededException("모집 완료");
        }
    }

    @Transactional(readOnly = true)
    public RaffleResultResponse getParticipationResult(Long eventId, UUID userUuid) {
        RaffleEvent event = raffleEventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("존재하지 않는 이벤트"));

        Participation participation = participationRepository
                .findByEventIdAndUserUuid(eventId, userUuid)
                .orElseThrow(() -> new ParticipationNotFoundException("참여 기록 없음"));

        validateResultVisibility(event);

        return RaffleResultResponse.fromEntity(participation);
    }

    private void validateResultVisibility(RaffleEvent event) {
        if (ZonedDateTime.now().isBefore(event.getResultAnnouncementTime())) {
            throw new ResultNotAvailableException("결과 발표 전입니다");
        }
    }

}
