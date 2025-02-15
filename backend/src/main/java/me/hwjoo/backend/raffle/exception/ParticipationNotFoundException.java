package me.hwjoo.backend.raffle.exception;

public class ParticipationNotFoundException extends RuntimeException {
    public ParticipationNotFoundException(String message) {
        super(message);
    }
}