package com.rankbook.hrms.automation.core.exceptions;

public class TargetEngineNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TargetEngineNotSupportedException(String message) {
        super(message);
    }
}