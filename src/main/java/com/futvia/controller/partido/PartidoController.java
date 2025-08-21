// src/main/java/com/futvia/controller/partido/PartidoController.java
package com.futvia.controller.partido;

import com.futvia.dto.partido.PartidoDTO;
import com.futvia.dto.partido.PartidoFormDTO;
import com.futvia.model.common.enums.EstadoPartido;
import com.futvia.service.partido.PartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
public class PartidoController {

    private final PartidoService partidoService;

    // GET /api/partidos?temporadaId=&estado=
    @GetMapping
    public ResponseEntity<Page<PartidoDTO>> listar(
            @RequestParam Long temporadaId,
            @RequestParam(required = false) EstadoPartido estado,
            Pageable pageable) {
        Page<PartidoDTO> page = partidoService.listar(temporadaId, estado, pageable);
        return ResponseEntity.ok(page);
        // Nota: el servicio ya maneja filtros de estado y temporada.
    }

    // GET /api/partidos/proximos?estado=PROGRAMADO&desde=2025-08-21T14:00
    @GetMapping("/proximos")
    public ResponseEntity<Page<PartidoDTO>> proximos(
            @RequestParam(required = false) EstadoPartido estado,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            Pageable pageable) {
        Page<PartidoDTO> page = partidoService.proximos(estado, desde, pageable);
        return ResponseEntity.ok(page);
    }

    // GET /api/partidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.obtener(id));
    }

    // POST /api/partidos
    @PostMapping
    public ResponseEntity<PartidoDTO> crear(@Valid @RequestBody PartidoFormDTO form) {
        PartidoDTO dto = partidoService.crear(form);
        return ResponseEntity.created(URI.create("/api/partidos/" + dto.getId())).body(dto);
    }

    // PUT /api/partidos/{id}/reprogramar
    @PutMapping("/{id}/reprogramar")
    public ResponseEntity<PartidoDTO> reprogramar(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nuevaFechaHora,
            @RequestParam Long nuevoEstadioId) {
        return ResponseEntity.ok(partidoService.reprogramar(id, nuevaFechaHora, nuevoEstadioId));
    }

    // PUT /api/partidos/{id}/estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<PartidoDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoPartido nuevoEstado) {
        return ResponseEntity.ok(partidoService.cambiarEstado(id, nuevoEstado));
    }

    // POST /api/partidos/{id}/cerrar
    @PostMapping("/{id}/cerrar")
    public ResponseEntity<PartidoDTO> cerrarYFijarMarcador(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.cerrarYFijarMarcador(id));
    }

    // DELETE /api/partidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/partidos/fixture?temporadaId=&equipoId=
    @GetMapping("/fixture")
    public ResponseEntity<Page<PartidoDTO>> fixtureEquipo(
            @RequestParam Long temporadaId,
            @RequestParam Long equipoId,
            Pageable pageable) {
        return ResponseEntity.ok(partidoService.fixtureEquipo(temporadaId, equipoId, pageable));
    }

    // GET /api/partidos/h2h?temporadaId=&equipoAId=&equipoBId=&max=5
    @GetMapping("/h2h")
    public ResponseEntity<List<PartidoDTO>> headToHead(
            @RequestParam Long temporadaId,
            @RequestParam Long equipoAId,
            @RequestParam Long equipoBId,
            @RequestParam(defaultValue = "5") int max) {
        return ResponseEntity.ok(partidoService.headToHead(temporadaId, equipoAId, equipoBId, max));
    }
}
