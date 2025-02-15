package me.hwjoo.backend.common.advice;

import me.hwjoo.backend.common.dto.ErrorResponse;
import me.hwjoo.backend.common.exception.ConcurrentParticipationException;
import me.hwjoo.backend.common.exception.InvalidDealStatusException;
import me.hwjoo.backend.common.exception.SoldOutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// src/main/java/me/hwjoo/backend/common/advice/GlobalExceptionHandler.java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SoldOutException.class)
    public ResponseEntity<ErrorResponse> handleSoldOut(SoldOutException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(
                        "SOLD_OUT",
                        "남은 재고 없음",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidDealStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(
            InvalidDealStatusException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        "INVALID_STATUS",
                        "유효하지 않은 상태",
                        ex.getMessage()
                ));
    }

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
}
