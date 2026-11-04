package com.rankbook.hrms.automation.core.exceptions;

public class GeofenceEvaluationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GeofenceEvaluationException(String message) {
        super(message);
    }

    public GeofenceEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}