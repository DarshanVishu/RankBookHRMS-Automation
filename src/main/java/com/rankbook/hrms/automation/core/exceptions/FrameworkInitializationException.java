package com.rankbook.hrms.automation.core.exceptions;

public class FrameworkInitializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FrameworkInitializationException(String message) {
        super(message);
    }

    public FrameworkInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}