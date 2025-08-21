// src/main/java/com/futvia/repository/audit/ExportLogRepository.java
package com.futvia.repository.audit;

import com.futvia.model.audit.ExportLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportLogRepository extends JpaRepository<ExportLog, Long> {

    // --- Últimos exports del usuario / por tipo ---
    Page<ExportLog> findByUsuario_Id(Long usuarioId, Pageable pageable);
    Page<ExportLog> findByTipo(String tipo, Pageable pageable);
    List<ExportLog> findTop20ByUsuario_IdOrderByCreatedAtDesc(Long usuarioId);

    // --- Rango de fechas (auditoría de exports) ---
    Page<ExportLog> findByCreatedAtBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);
    Page<ExportLog> findByUsuario_IdAndCreatedAtBetween(Long usuarioId, LocalDateTime desde, LocalDateTime hasta, Pageable pageable);

    // --- Vinculados a archivo generado (S3/Contenido) ---
    Optional<ExportLog> findTop1ByArchivo_IdOrderByCreatedAtDesc(Long archivoId);
}
