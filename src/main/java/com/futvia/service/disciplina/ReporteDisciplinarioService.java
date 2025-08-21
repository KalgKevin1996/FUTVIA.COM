// src/main/java/com/futvia/service/disciplina/ReporteDisciplinarioService.java
package com.futvia.service.disciplina;

import com.futvia.dto.disciplina.ReporteDisciplinarioDTO;
import com.futvia.dto.disciplina.ReporteDisciplinarioFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReporteDisciplinarioService {
    ReporteDisciplinarioDTO obtener(Long id);
    Page<ReporteDisciplinarioDTO> listarPorPartido(Long partidoId, Pageable pageable);
    Page<ReporteDisciplinarioDTO> listarPorTemporada(Long temporadaId, Pageable pageable);
    Page<ReporteDisciplinarioDTO> listarPorReportante(Long usuarioId, Pageable pageable);
    Page<ReporteDisciplinarioDTO> buscar(String q, Pageable pageable);

    ReporteDisciplinarioDTO crear(ReporteDisciplinarioFormDTO form);
    ReporteDisciplinarioDTO actualizar(Long id, ReporteDisciplinarioFormDTO form);
    void eliminar(Long id);
}
