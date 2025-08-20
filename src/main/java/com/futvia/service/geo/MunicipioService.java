package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import org.springframework.data.domain.*;

public interface MunicipioService {
    Page<MunicipioDTO> listar(Pageable p);
    Page<MunicipioDTO> porDepartamento(Long departamentoId, Pageable p);
    MunicipioDTO crear(MunicipioFormDTO f);
    MunicipioDTO editar(Long id, MunicipioFormDTO f);
    void eliminar(Long id);
}