// src/main/java/com/futvia/controller/audit/ExportLogController.java
package com.futvia.controller.audit;

import com.futvia.dto.audit.ExportLogDTO;
import com.futvia.dto.common.PageResponseDTO;
import com.futvia.service.audit.ExportLogService;
import com.futvia.service.common.PageResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/audit/exports")
@RequiredArgsConstructor
public class ExportLogController {
    private final ExportLogService service;

    /** Búsqueda paginada con filtros opcionales. */
    @GetMapping
    public ResponseEntity<PageResponseDTO<ExportLogDTO>> buscar(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<ExportLogDTO> page = service.buscar(usuarioId, tipo, desde, hasta, pageable);
        return ResponseEntity.ok(PageResponseFactory.from(page));
    }

    /** Registrar un export. */
    @PostMapping
    public ResponseEntity<ExportLogDTO> registrar(
            @RequestParam String tipo,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long archivoId,
            @RequestParam(required = false, name="parametrosJson") String parametrosJson
    ) {
        return ResponseEntity.ok(service.registrar(tipo, usuarioId, archivoId, parametrosJson));
    }

    /** Último export asociado a un archivo. */
    @GetMapping("/archivo/{archivoId}/ultimo")
    public ResponseEntity<ExportLogDTO> ultimoPorArchivo(@PathVariable Long archivoId) {
        Optional<ExportLogDTO> opt = service.ultimoPorArchivo(archivoId);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
