package com.example.examplesocialnetwork.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User with id = '" + userId + "' not found");
    }
}
