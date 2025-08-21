// src/main/java/com/futvia/service/audit/AuditLogService.java
package com.futvia.service.audit;

import com.futvia.dto.audit.AuditLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditLogService {

    /** Búsqueda flexible con filtros opcionales. */
    Page<AuditLogDTO> buscar(
            String entidad, Long entidadId,
            Long usuarioId, String accion,
            LocalDateTime desde, LocalDateTime hasta,
            String q, Pageable pageable);

    /** Últimos N registros de una entidad específica. */
    List<AuditLogDTO> ultimosDeEntidad(String entidad, Long entidadId, int limit);

    /** Registrar una acción en la bitácora. */
    AuditLogDTO registrar(String accion, String entidad, Long entidadId, Long usuarioId, String detalle, String ip);
}
