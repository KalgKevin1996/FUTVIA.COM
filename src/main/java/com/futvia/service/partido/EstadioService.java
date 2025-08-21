package com.futvia.service.partido;

import com.futvia.dto.partido.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstadioService {
    Page<EstadioDTO> listar(Pageable pageable);
    EstadioDTO obtener(Long id);
    EstadioDTO crear(EstadioFormDTO form);
    EstadioDTO actualizar(Long id, EstadioFormDTO form);
    void eliminar(Long id);
}
