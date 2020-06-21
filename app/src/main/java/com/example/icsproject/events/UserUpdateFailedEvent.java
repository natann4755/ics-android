package com.example.icsproject.events;

public class UserUpdateFailedEvent {
    private final String message;

    public UserUpdateFailedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
