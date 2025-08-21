// src/main/java/com/futvia/service/evento/EstadisticaEquipoPartidoService.java
package com.futvia.service.evento;

import com.futvia.dto.evento.EstadisticaEquipoPartidoDTO;
import com.futvia.dto.evento.EstadisticaEquipoPartidoFormDTO;

import java.util.List;

public interface EstadisticaEquipoPartidoService {
    // upsert por (partido, equipo)
    EstadisticaEquipoPartidoDTO guardarOActualizar(EstadisticaEquipoPartidoFormDTO form);

    EstadisticaEquipoPartidoDTO obtener(Long partidoId, Long equipoId);
    List<EstadisticaEquipoPartidoDTO> listarPorPartido(Long partidoId);

    void eliminar(Long id);
}
