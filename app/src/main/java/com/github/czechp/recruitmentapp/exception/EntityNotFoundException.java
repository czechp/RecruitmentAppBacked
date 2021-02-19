package com.github.czechp.recruitmentapp.exception;

public class EntityNotFoundException extends RuntimeException {
    EntityNotFoundException(String message) {
        super(message);
    }
}
