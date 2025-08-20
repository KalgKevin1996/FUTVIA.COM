package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import org.springframework.data.domain.*;

public interface DepartamentoService {
    Page<DepartamentoDTO> listar(Pageable p);
    Page<DepartamentoDTO> porPais(Long paisId, Pageable p);
    DepartamentoDTO crear(DepartamentoFormDTO f);
    DepartamentoDTO editar(Long id, DepartamentoFormDTO f);
    void eliminar(Long id);
}