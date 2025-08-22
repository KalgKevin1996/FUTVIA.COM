package com.futvia.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple readiness probe.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        Map<String, Object> info = new HashMap<>();
        info.put("app", "Futvia");
        info.put("status", "UP");
        info.put("version", "v1");
        return ResponseEntity.ok(ApiResponse.ok(info, "/api/health"));
    }
}