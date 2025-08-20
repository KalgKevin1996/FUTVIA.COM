package com.futvia.service.club;

import com.futvia.dto.club.*;
import org.springframework.data.domain.*;

public interface ArbitroService {
    Page<ArbitroDTO> listar(Pageable p);
    Page<ArbitroDTO> buscar(String q, Pageable p);
    ArbitroDTO crear(ArbitroFormDTO f);
    ArbitroDTO editar(Long id, ArbitroFormDTO f);
    void eliminar(Long id);
}