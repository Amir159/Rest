package com.syncretis.exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(HttpStatus status) {
        super("Person " + status.getReasonPhrase());
    }
}
