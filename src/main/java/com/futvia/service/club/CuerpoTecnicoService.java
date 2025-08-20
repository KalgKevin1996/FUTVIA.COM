package com.futvia.service.club;

import com.futvia.dto.club.*;
import org.springframework.data.domain.*;

public interface CuerpoTecnicoService {
    Page<CuerpoTecnicoDTO> porEquipo(Long equipoId, Pageable p);
    CuerpoTecnicoDTO crear(CuerpoTecnicoFormDTO f);
    CuerpoTecnicoDTO editar(Long id, CuerpoTecnicoFormDTO f);
    void eliminar(Long id);
}