package com.futvia.service.competicion;

import com.futvia.dto.competicion.*; import org.springframework.data.domain.*;

public interface CompeticionService {
    Page<CompeticionDTO> listar(Pageable p);
    Page<CompeticionDTO> buscarPorNombre(String q, Pageable p);
    CompeticionDTO crear(CompeticionFormDTO f);
    CompeticionDTO editar(Long id, CompeticionFormDTO f);
    void eliminar(Long id);
}