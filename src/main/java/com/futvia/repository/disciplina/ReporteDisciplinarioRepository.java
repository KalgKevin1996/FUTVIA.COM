// src/main/java/com/futvia/repository/disciplina/ReporteDisciplinarioRepository.java
package com.futvia.repository.disciplina;

import com.futvia.model.disciplina.ReporteDisciplinario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReporteDisciplinarioRepository extends JpaRepository<ReporteDisciplinario, Long> {

    // Listar por partido
    List<ReporteDisciplinario> findByPartido_Id(Long partidoId);

    // Paginado por partido (útil para vistas largas)
    Page<ReporteDisciplinario> findByPartido_Id(Long partidoId, Pageable pageable);

    // Listar por reportante (árbitro/comisario)
    Page<ReporteDisciplinario> findByReportante_Id(Long usuarioId, Pageable pageable);

    // Reportes de una temporada (vía relación con Partido -> Temporada)
    @Query("""
           select r from ReporteDisciplinario r
             where r.partido.temporada.id = :temporadaId
           """)
    Page<ReporteDisciplinario> findByTemporada(@Param("temporadaId") Long temporadaId, Pageable pageable);

    // Búsqueda simple por texto en descripción (QLIKE)
    @Query("""
           select r from ReporteDisciplinario r
             where (:q is null or lower(r.descripcion) like lower(concat('%', :q, '%')))
           """)
    Page<ReporteDisciplinario> search(@Param("q") String q, Pageable pageable);
}
