package me.hwjoo.backend.raffle.exception;

public class EventExpiredException extends RuntimeException {
    public EventExpiredException(String message) {
        super(message);
    }
}