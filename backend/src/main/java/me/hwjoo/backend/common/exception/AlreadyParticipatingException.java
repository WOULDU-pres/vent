package me.hwjoo.backend.common.exception;

/**
 * 이미 참여 중임을 알리는 커스텀 예외.
 */
public class AlreadyParticipatingException extends RuntimeException {
    public AlreadyParticipatingException(String message) {
        super(message);
    }
}