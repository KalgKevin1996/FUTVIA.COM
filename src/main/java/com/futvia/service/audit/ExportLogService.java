// src/main/java/com/futvia/service/audit/ExportLogService.java
package com.futvia.service.audit;

import com.futvia.dto.audit.ExportLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExportLogService {

    /** Búsqueda con filtros opcionales (usuario, tipo, rango de fechas). */
    Page<ExportLogDTO> buscar(Long usuarioId, String tipo, LocalDateTime desde, LocalDateTime hasta, Pageable pageable);

    /** Registrar un export (con vínculo a Archivo si aplica). */
    ExportLogDTO registrar(String tipo, Long usuarioId, Long archivoId, String parametrosJson);

    /** Último export generado para un archivo. */
    Optional<ExportLogDTO> ultimoPorArchivo(Long archivoId);
}
