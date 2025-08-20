// src/main/java/com/futvia/service/club/CuerpoTecnicoService.java
package com.futvia.service.club;

import com.futvia.dto.club.CuerpoTecnicoDTO;
import com.futvia.dto.club.CuerpoTecnicoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CuerpoTecnicoService {
    Page<CuerpoTecnicoDTO> listar(Pageable pageable);
    List<CuerpoTecnicoDTO> listarPorEquipo(Long equipoId);
    CuerpoTecnicoDTO obtener(Long id);
    CuerpoTecnicoDTO crear(CuerpoTecnicoFormDTO form);
    CuerpoTecnicoDTO editar(Long id, CuerpoTecnicoFormDTO form);
    void eliminar(Long id);
}
