package me.hwjoo.backend.raffle.exception;

public class MaxParticipantsExceededException extends RuntimeException {
    public MaxParticipantsExceededException(String message) {
        super(message);
    }
}
