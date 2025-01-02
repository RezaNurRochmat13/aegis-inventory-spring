package com.aegis.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientProductStock extends RuntimeException {
    public InsufficientProductStock(String message) {
        super(message);
    }

    public InsufficientProductStock(String message, Throwable cause) {
        super(message, cause);
    }
}
