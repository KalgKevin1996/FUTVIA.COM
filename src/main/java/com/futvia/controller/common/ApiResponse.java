package com.futvia.controller.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;

/**
 * Generic API response wrapper to keep a consistent payload shape across controllers.
 * success: operation result
 * message: short human-readable message
 * data:    payload (nullable)
 * timestamp/path: meta
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private OffsetDateTime timestamp;
    private String path;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message, T data, String path) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = OffsetDateTime.now();
        this.path = path;
    }

    public static <T> ApiResponse<T> ok(T data, String message, String path) {
        return new ApiResponse<>(true, message, data, path);
    }

    public static <T> ApiResponse<T> ok(T data, String path) {
        return ok(data, "OK", path);
    }

    public static <T> ApiResponse<T> ok(String message, String path) {
        return ok(null, message, path);
    }

    public static <T> ApiResponse<T> error(String message, String path) {
        return new ApiResponse<>(false, message, null, path);
    }

    // Getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}