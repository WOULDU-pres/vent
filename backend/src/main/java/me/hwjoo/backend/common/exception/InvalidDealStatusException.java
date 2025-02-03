package me.hwjoo.backend.common.exception;

/**
 * 딜 상태가 유효하지 않을 경우 발생하는 예외.
 */
public class InvalidDealStatusException extends RuntimeException {
    public InvalidDealStatusException(String message) {
        super(message);
    }
}