package com.syncretis.exception;

import org.springframework.http.HttpStatus;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(HttpStatus status) {
        super("Document " + status.getReasonPhrase());
    }
}