package me.hwjoo.backend.common.exception;

public class ConcurrentParticipationException extends RuntimeException {
    public ConcurrentParticipationException(String message) {
        super(message);
    }
}