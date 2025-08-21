package com.futvia.service.partido;

import com.futvia.dto.partido.*;
import com.futvia.model.common.enums.EstadoPartido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
public interface PartidoService {
    Page<PartidoDTO> listar(Long temporadaId, EstadoPartido estado, Pageable pageable);
    Page<PartidoDTO> proximos(EstadoPartido estado, LocalDateTime desde, Pageable pageable);
    PartidoDTO obtener(Long id);
    PartidoDTO crear(PartidoFormDTO form);
    PartidoDTO reprogramar(Long id, LocalDateTime nuevaFechaHora, Long nuevoEstadioId);
    PartidoDTO cambiarEstado(Long id, EstadoPartido nuevoEstado);
    PartidoDTO cerrarYFijarMarcador(Long id); // calcula goles desde incidencias y pone FINALIZADO
    void eliminar(Long id);

    // extras útiles
    Page<PartidoDTO> fixtureEquipo(Long temporadaId, Long equipoId, Pageable pageable);
    List<PartidoDTO> headToHead(Long temporadaId, Long equipoAId, Long equipoBId, int maxResultados);
}