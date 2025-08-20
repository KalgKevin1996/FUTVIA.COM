// src/main/java/com/futvia/service/common/BusinessException.java
package com.futvia.service.common;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
