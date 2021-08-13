package com.syncretis.exception;

import org.springframework.http.HttpStatus;

public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException(HttpStatus status) {
        super("Language " + status.getReasonPhrase());
    }
}
