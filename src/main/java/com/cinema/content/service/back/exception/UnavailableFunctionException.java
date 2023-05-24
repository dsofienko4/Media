package com.cinema.content.service.back.exception;

public class UnavailableFunctionException extends RuntimeException {
    public UnavailableFunctionException(String message) {
        super(message);
    }
}