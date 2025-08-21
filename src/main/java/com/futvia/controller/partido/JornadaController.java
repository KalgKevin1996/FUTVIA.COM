// src/main/java/com/futvia/controller/partido/JornadaController.java
package com.futvia.controller.partido;

import com.futvia.dto.partido.JornadaDTO;
import com.futvia.dto.partido.JornadaFormDTO;
import com.futvia.service.partido.JornadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/jornadas")
@RequiredArgsConstructor
public class JornadaController {

    private final JornadaService jornadaService;

    // GET /api/jornadas?temporadaId=
    @GetMapping
    public ResponseEntity<Page<JornadaDTO>> listarPorTemporada(
            @RequestParam Long temporadaId,
            Pageable pageable) {
        return ResponseEntity.ok(jornadaService.listarPorTemporada(temporadaId, pageable));
    }

    // GET /api/jornadas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<JornadaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(jornadaService.obtener(id));
    }

    // GET /api/jornadas/buscar?temporadaId=&numero=
    @GetMapping("/buscar")
    public ResponseEntity<JornadaDTO> buscarPorNumero(
            @RequestParam Long temporadaId,
            @RequestParam Integer numero) {
        Optional<JornadaDTO> opt = jornadaService.buscarPorNumero(temporadaId, numero);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/jornadas/rango?temporadaId=&desde=2025-08-01&hasta=2025-08-31
    @GetMapping("/rango")
    public ResponseEntity<Page<JornadaDTO>> listarRangoFechas(
            @RequestParam Long temporadaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            Pageable pageable) {
        return ResponseEntity.ok(jornadaService.listarRangoFechas(temporadaId, desde, hasta, pageable));
    }

    // POST /api/jornadas
    @PostMapping
    public ResponseEntity<JornadaDTO> crear(@Valid @RequestBody JornadaFormDTO form) {
        JornadaDTO dto = jornadaService.crear(form);
        return ResponseEntity.created(URI.create("/api/jornadas/" + dto.getId())).body(dto);
    }

    // PUT /api/jornadas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<JornadaDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody JornadaFormDTO form) {
        return ResponseEntity.ok(jornadaService.actualizar(id, form));
    }

    // DELETE /api/jornadas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        jornadaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
