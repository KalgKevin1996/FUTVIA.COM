// src/main/java/com/futvia/repository/audit/AuditLogRepository.java
package com.futvia.repository.audit;

import com.futvia.model.audit.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    // --- Búsquedas por entidad afectada (usa el índice entidad+entidadId) ---
    Page<AuditLog> findByEntidadAndEntidadIdOrderByCreatedAtDesc(String entidad, Long entidadId, Pageable pageable);
    List<AuditLog> findTop20ByEntidadAndEntidadIdOrderByCreatedAtDesc(String entidad, Long entidadId);

    // --- Filtros por usuario / acción ---
    Page<AuditLog> findByUsuario_Id(Long usuarioId, Pageable pageable);
    Page<AuditLog> findByAccion(String accion, Pageable pageable);
    long countByAccionAndEntidadAndEntidadId(String accion, String entidad, Long entidadId);

    // --- Rango de fechas (para reportes y dashboards) ---
    Page<AuditLog> findByCreatedAtBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
    Page<AuditLog> findByUsuario_IdAndCreatedAtBetween(Long usuarioId, LocalDateTime desde, LocalDateTime hasta, Pageable pageable);

    // --- Búsqueda simple por texto (detalle/ip) ---
    @Query("""
           select a from AuditLog a
           where (:q is null or lower(a.detalle) like lower(concat('%', :q, '%')) or lower(a.ip) like lower(concat('%', :q, '%')))
           """)
    Page<AuditLog> searchByDetalleOrIp(@Param("q") String q, Pageable pageable);
}
