// src/main/java/com/futvia/controller/audit/AuditLogController.java
package com.futvia.controller.audit;

import com.futvia.dto.audit.AuditLogDTO;
import com.futvia.dto.common.PageResponseDTO;
import com.futvia.service.audit.AuditLogService;
import com.futvia.service.common.PageResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/audit/logs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogService service;

    /** Búsqueda con filtros opcionales. */
    @GetMapping
    public ResponseEntity<PageResponseDTO<AuditLogDTO>> buscar(
            @RequestParam(required = false) String entidad,
            @RequestParam(required = false) Long entidadId,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String accion,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<AuditLogDTO> page = service.buscar(entidad, entidadId, usuarioId, accion, desde, hasta, q, pageable);
        return ResponseEntity.ok(PageResponseFactory.from(page));
    }

    /** Últimos N registros para una entidad específica. */
    @GetMapping("/entidad/{entidad}/{entidadId}/ultimos")
    public ResponseEntity<List<AuditLogDTO>> ultimosDeEntidad(
            @PathVariable String entidad,
            @PathVariable Long entidadId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(service.ultimosDeEntidad(entidad, entidadId, limit));
    }

    /** Registrar manualmente (útil para pruebas o integraciones externas). */
    @PostMapping
    public ResponseEntity<AuditLogDTO> registrar(
            @RequestParam String accion,
            @RequestParam String entidad,
            @RequestParam Long entidadId,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String detalle,
            @RequestParam(required = false) String ip
    ) {
        return ResponseEntity.ok(service.registrar(accion, entidad, entidadId, usuarioId, detalle, ip));
    }
}
