package com.syncretis.exception;

public class DepartmentBadRequestException extends RuntimeException {

    public DepartmentBadRequestException(String status) {
        super(status);
    }
}
