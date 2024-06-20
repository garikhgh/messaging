package com.exam.messaging.exception;

public class NotAllowedInstanceCreationException extends RuntimeException {
    public NotAllowedInstanceCreationException(String msg) {
        super(msg);
    }
}
