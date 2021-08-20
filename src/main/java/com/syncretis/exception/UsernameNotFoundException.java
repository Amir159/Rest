package com.syncretis.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(HttpStatus status) {
        super("Username " + status.getReasonPhrase());
    }
}
