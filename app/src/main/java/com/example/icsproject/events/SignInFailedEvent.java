package com.example.icsproject.events;

public class SignInFailedEvent {

    private final String message;

    public SignInFailedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
