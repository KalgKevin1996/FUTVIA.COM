package com.futvia.service.evento;

import com.futvia.dto.evento.IncidenciaPartidoDTO;
import com.futvia.dto.evento.IncidenciaPartidoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidenciaPartidoService {
    Page<IncidenciaPartidoDTO> listarPorPartido(Long partidoId, Pageable pageable);
    Page<IncidenciaPartidoDTO> listarPorPartidoYEquipo(Long partidoId, Long equipoId, Pageable pageable);

    IncidenciaPartidoDTO crear(IncidenciaPartidoFormDTO form);
    IncidenciaPartidoDTO editar(Long id, IncidenciaPartidoFormDTO form);
    void eliminar(Long id);

    // Agregados útiles para marcador / reportes
    long golesAFavor(Long partidoId, Long equipoId);          // GOL del equipo
    long autogolesDelRival(Long partidoId, Long equipoId);    // AUTOGOL del rival
    default long marcadorCalculado(Long partidoId, Long equipoId) {
        return golesAFavor(partidoId, equipoId) + autogolesDelRival(partidoId, equipoId);
    }

    long golesDeJugadorEnTemporada(Long jugadorId, Long temporadaId);
}
