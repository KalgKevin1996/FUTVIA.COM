package com.futvia.controller.evento;

import com.futvia.dto.evento.IncidenciaPartidoDTO;
import com.futvia.dto.evento.IncidenciaPartidoFormDTO;
import com.futvia.service.evento.IncidenciaPartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/eventos")
@Validated
@RequiredArgsConstructor
public class IncidenciaPartidoController {

    private final IncidenciaPartidoService service;

    // --- Listados ---
    @GetMapping("/partidos/{partidoId}/incidencias")
    public ResponseEntity<Page<IncidenciaPartidoDTO>> listarPorPartido(@PathVariable Long partidoId, Pageable pageable) {
        return ResponseEntity.ok(service.listarPorPartido(partidoId, pageable));
    }

    @GetMapping("/partidos/{partidoId}/equipos/{equipoId}/incidencias")
    public ResponseEntity<Page<IncidenciaPartidoDTO>> listarPorPartidoYEquipo(@PathVariable Long partidoId,
                                                                               @PathVariable Long equipoId,
                                                                               Pageable pageable) {
        return ResponseEntity.ok(service.listarPorPartidoYEquipo(partidoId, equipoId, pageable));
    }

    // --- CRUD ---
    @PostMapping("/incidencias")
    public ResponseEntity<IncidenciaPartidoDTO> crear(@Valid @RequestBody IncidenciaPartidoFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/incidencias/{id}")
    public ResponseEntity<IncidenciaPartidoDTO> editar(@PathVariable Long id, @Valid @RequestBody IncidenciaPartidoFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/incidencias/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Agregados / Métricas ---
    @GetMapping("/partidos/{partidoId}/equipos/{equipoId}/goles-a-favor")
    public ResponseEntity<Map<String, Long>> golesAFavor(@PathVariable Long partidoId, @PathVariable Long equipoId) {
        long v = service.golesAFavor(partidoId, equipoId);
        return ResponseEntity.ok(Map.of("value", v));
    }

    @GetMapping("/partidos/{partidoId}/equipos/{equipoId}/autogoles-del-rival")
    public ResponseEntity<Map<String, Long>> autogolesDelRival(@PathVariable Long partidoId, @PathVariable Long equipoId) {
        long v = service.autogolesDelRival(partidoId, equipoId);
        return ResponseEntity.ok(Map.of("value", v));
    }

    @GetMapping("/partidos/{partidoId}/equipos/{equipoId}/marcador")
    public ResponseEntity<Map<String, Long>> marcadorCalculado(@PathVariable Long partidoId, @PathVariable Long equipoId) {
        long v = service.marcadorCalculado(partidoId, equipoId);
        return ResponseEntity.ok(Map.of("value", v));
    }

    @GetMapping("/jugadores/{jugadorId}/temporadas/{temporadaId}/goles")
    public ResponseEntity<Map<String, Long>> golesDeJugadorEnTemporada(@PathVariable Long jugadorId, @PathVariable Long temporadaId) {
        long v = service.golesDeJugadorEnTemporada(jugadorId, temporadaId);
        return ResponseEntity.ok(Map.of("value", v));
    }
}
