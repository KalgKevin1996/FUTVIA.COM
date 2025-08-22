package com.futvia.controller.evento;

import com.futvia.dto.evento.EstadisticaEquipoPartidoDTO;
import com.futvia.dto.evento.EstadisticaEquipoPartidoFormDTO;
import com.futvia.service.evento.EstadisticaEquipoPartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@Validated
@RequiredArgsConstructor
public class EstadisticaEquipoPartidoController {

    private final EstadisticaEquipoPartidoService service;

    /** Upsert por (partidoId, equipoId). */
    @PutMapping("/estadisticas-equipo-partido")
    public ResponseEntity<EstadisticaEquipoPartidoDTO> guardarOActualizar(@Valid @RequestBody EstadisticaEquipoPartidoFormDTO form) {
        return ResponseEntity.ok(service.guardarOActualizar(form));
    }

    /** Obtener la estadística de un equipo en un partido específico. */
    @GetMapping("/partidos/{partidoId}/equipos/{equipoId}/estadistica")
    public ResponseEntity<EstadisticaEquipoPartidoDTO> obtener(@PathVariable Long partidoId, @PathVariable Long equipoId) {
        return ResponseEntity.ok(service.obtener(partidoId, equipoId));
    }

    /** Listar estadísticas por partido. */
    @GetMapping("/partidos/{partidoId}/estadisticas-equipo")
    public ResponseEntity<List<EstadisticaEquipoPartidoDTO>> listarPorPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(service.listarPorPartido(partidoId));
    }

    /** Eliminar por id de estadística. */
    @DeleteMapping("/estadisticas-equipo-partido/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
