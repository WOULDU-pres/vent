package me.hwjoo.backend.common.advice;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.hwjoo.backend.common.exception.InvalidDealStatusException;
import me.hwjoo.backend.common.exception.SoldOutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// src/main/java/me/hwjoo/backend/common/advice/GlobalExceptionHandler.java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Getter
    @AllArgsConstructor
    public class ErrorResponse {
        private String code;
        private String message;
        private final Instant timestamp = Instant.now();
    }

    @ExceptionHandler(SoldOutException.class)
    public ResponseEntity<ErrorResponse> handleSoldOut(SoldOutException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("SOLD_OUT", ex.getMessage()));
    }

    @ExceptionHandler(InvalidDealStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(InvalidDealStatusException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("INVALID_STATUS", ex.getMessage()));
    }
}
