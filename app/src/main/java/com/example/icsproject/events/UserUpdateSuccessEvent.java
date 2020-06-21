package com.example.icsproject.events;

public class UserUpdateSuccessEvent {
    private final String message;

    public UserUpdateSuccessEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
