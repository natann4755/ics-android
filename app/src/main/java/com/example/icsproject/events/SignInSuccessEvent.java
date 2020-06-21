package com.example.icsproject.events;

public class SignInSuccessEvent {

    private final String message;

    public SignInSuccessEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
