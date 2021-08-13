package com.syncretis.exception;

import org.springframework.http.HttpStatus;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(HttpStatus status) {
        super("Department " + status.getReasonPhrase());
    }
}
