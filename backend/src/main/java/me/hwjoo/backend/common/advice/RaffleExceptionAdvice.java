package me.hwjoo.backend.common.advice;

import me.hwjoo.backend.common.dto.ErrorResponse;
import me.hwjoo.backend.common.exception.ConcurrentParticipationException;
import me.hwjoo.backend.common.exception.TooManyRequestsException;
import me.hwjoo.backend.raffle.exception.RaffleProcessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// src/main/java/me/hwjoo/backend/common/advice/RaffleExceptionAdvice.java
@RestControllerAdvice
public class RaffleExceptionAdvice {

    @ExceptionHandler(ConcurrentParticipationException.class)
    public ResponseEntity<ErrorResponse> handleConcurrentError(
            ConcurrentParticipationException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(
                        "RAFFLE_001",
                        "동시 요청 처리 실패",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(RaffleProcessException.class)
    public ResponseEntity<ErrorResponse> handleProcessError(
            RaffleProcessException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        "RAFFLE_500",
                        "추첨 시스템 오류",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(ConcurrentParticipationException.class)
    public ResponseEntity<ErrorResponse> handleConcurrencyConflict(
            ConcurrentParticipationException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(
                        "RAFFLE_409_1",
                        "동시 참여 충돌",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ErrorResponse> handleRateLimitExceeded(
            TooManyRequestsException ex
    ) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(ErrorResponse.of(
                        "RAFFLE_429_1",
                        "요청 제한 초과",
                        "1분 후 다시 시도해주세요"
                ));
    }

}

