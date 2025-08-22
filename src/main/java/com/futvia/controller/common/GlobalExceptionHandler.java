package com.futvia.controller.common;

import com.futvia.service.common.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Centralized handler so all controllers in the app get consistent error responses.
 * Returns ApiResponse<?> with success=false.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> onNotFound(NotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> onValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (a,b)->a));
        return build(HttpStatus.BAD_REQUEST, "Validación fallida", req, errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> onConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        }
        return build(HttpStatus.BAD_REQUEST, "Parámetros inválidos", req, errors);
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiResponse<Object>> onBadRequest(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> onIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "Violación de integridad de datos", req);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> onMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), req);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<Object>> onMediaType(HttpMediaTypeNotSupportedException ex, HttpServletRequest req) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> onGeneric(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", req);
    }

    private ResponseEntity<ApiResponse<Object>> build(HttpStatus status, String message, HttpServletRequest req) {
        ApiResponse<Object> body = ApiResponse.error(message, req.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<ApiResponse<Object>> build(HttpStatus status, String message, HttpServletRequest req, Object data) {
        ApiResponse<Object> body = ApiResponse.ok(data, message, req.getRequestURI());
        body.setSuccess(false); // ensure flag is false for error responses with payload
        return ResponseEntity.status(status).body(body);
    }
}