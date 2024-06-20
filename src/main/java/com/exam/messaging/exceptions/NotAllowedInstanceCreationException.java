package com.exam.messaging.exceptions;

public class NotAllowedInstanceCreationException extends RuntimeException {
    public NotAllowedInstanceCreationException(String msg) {
        super(msg);
    }
}
