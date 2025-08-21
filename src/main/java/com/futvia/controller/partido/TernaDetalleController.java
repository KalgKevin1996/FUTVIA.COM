// src/main/java/com/futvia/controller/partido/TernaDetalleController.java
package com.futvia.controller.partido;

import com.futvia.dto.partido.TernaDetalleDTO;
import com.futvia.dto.partido.TernaDetalleFormDTO;
import com.futvia.service.partido.TernaDetalleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/terna-detalles")
@RequiredArgsConstructor
public class TernaDetalleController {

    private final TernaDetalleService ternaDetalleService;

    // GET /api/terna-detalles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TernaDetalleDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ternaDetalleService.obtener(id));
    }

    // GET /api/terna-detalles?ternaId=
    @GetMapping
    public ResponseEntity<List<TernaDetalleDTO>> listarPorTerna(@RequestParam Long ternaId) {
        return ResponseEntity.ok(ternaDetalleService.listarPorTerna(ternaId));
    }

    // POST /api/terna-detalles
    @PostMapping
    public ResponseEntity<TernaDetalleDTO> crear(@Valid @RequestBody TernaDetalleFormDTO form) {
        TernaDetalleDTO dto = ternaDetalleService.crear(form);
        return ResponseEntity.created(URI.create("/api/terna-detalles/" + dto.getId())).body(dto);
    }

    // PUT /api/terna-detalles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TernaDetalleDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TernaDetalleFormDTO form) {
        return ResponseEntity.ok(ternaDetalleService.actualizar(id, form));
    }

    // DELETE /api/terna-detalles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ternaDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
