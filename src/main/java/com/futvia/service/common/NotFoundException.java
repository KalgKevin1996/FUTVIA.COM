package com.futvia.service.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}