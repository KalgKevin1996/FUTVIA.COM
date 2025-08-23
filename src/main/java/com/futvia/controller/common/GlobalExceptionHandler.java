package com.futvia.controller.common;

import com.futvia.service.common.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /* ========= Helpers ========= */

    private ResponseEntity<ErrorResponse> build(
            HttpServletRequest req,
            HttpStatus status,
            String message,
            List<String> errors
    ) {
        ErrorResponse body = new ErrorResponse(
                OffsetDateTime.now(),
                req.getMethod(),
                req.getRequestURI(),
                status.value(),
                status.getReasonPhrase(),
                message,
                (errors == null || errors.isEmpty()) ? null : errors
        );
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }

    private static String safeMessage(Throwable ex) {
        String msg = Optional.ofNullable(ex.getMessage()).orElse(ex.getClass().getSimpleName());
        if (msg != null) {
            String low = msg.toLowerCase();
            // evita filtrar detalles de SQL o esquema
            if (low.contains("sql") || low.contains("constraint") || low.contains("column")) {
                return "Conflicto de integridad de datos";
            }
        }
        return msg;
    }

    /* ========= 400: Bad Request ========= */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                errors.add(fe.getField() + ": " + fe.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(ge ->
                errors.add(ge.getObjectName() + ": " + ge.getDefaultMessage()));
        return build(req, HttpStatus.BAD_REQUEST, "Validación fallida", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest req
    ) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            errors.add(v.getPropertyPath() + ": " + v.getMessage());
        }
        return build(req, HttpStatus.BAD_REQUEST, "Violación de restricciones", errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest req
    ) {
        String expected = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "otro tipo";
        String msg = "Parámetro inválido '" + ex.getName() + "': se esperaba " + expected;
        return build(req, HttpStatus.BAD_REQUEST, msg, null);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex,
            HttpServletRequest req
    ) {
        String msg = "Falta el parámetro requerido '" + ex.getParameterName() + "'";
        return build(req, HttpStatus.BAD_REQUEST, msg, null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest req
    ) {
        return build(req, HttpStatus.BAD_REQUEST, "Solicitud mal formada", null);
    }

    /* ========= 401 / 403 ========= */

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest req
    ) {
        return build(req, HttpStatus.UNAUTHORIZED, "Credenciales inválidas", null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest req
    ) {
        return build(req, HttpStatus.FORBIDDEN, "Acceso denegado", null);
    }

    /* ========= 404 ========= */

    @ExceptionHandler({
            NotFoundException.class,
            EntityNotFoundException.class,
            NoSuchElementException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(
            RuntimeException ex,
            HttpServletRequest req
    ) {
        return build(req, HttpStatus.NOT_FOUND, "Recurso no encontrado", List.of(safeMessage(ex)));
    }

    /* ========= 405 ========= */

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest req
    ) {
        String[] supported = ex.getSupportedMethods();
        String allowed = (supported == null || supported.length == 0)
                ? ""
                : String.join(", ", Arrays.asList(supported));
        String msg = allowed.isEmpty()
                ? "Método no permitido"
                : "Método no permitido. Permitidos: " + allowed;
        return build(req, HttpStatus.METHOD_NOT_ALLOWED, msg, null);
    }

    /* ========= 409 ========= */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest req
    ) {
        List<String> errors = new ArrayList<>();
        errors.add(safeMessage(ex));
        if (ex.getCause() != null) {
            errors.add("Causa: " + safeMessage(ex.getCause()));
        }
        return build(req, HttpStatus.CONFLICT, "Conflicto de integridad de datos", errors);
    }

    /* ========= 500 (catch-all) ========= */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(
            Exception ex,
            HttpServletRequest req
    ) {
        // TODO: logger.error("Unhandled exception", ex);
        return build(req, HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", List.of(ex.getClass().getSimpleName()));
    }

    /* ========= DTO de error ========= */
    public record ErrorResponse(
            OffsetDateTime timestamp,
            String method,
            String path,
            int status,
            String error,
            String message,
            List<String> errors
    ) {}
}
