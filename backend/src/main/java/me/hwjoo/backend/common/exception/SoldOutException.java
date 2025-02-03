package me.hwjoo.backend.common.exception;

/**
 * 재고 부족 시 발생하는 예외.
 */
public class SoldOutException extends RuntimeException {
    public SoldOutException(String message) {
        super(message);
    }
}