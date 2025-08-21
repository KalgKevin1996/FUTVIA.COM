// src/main/java/com/futvia/controller/partido/EstadioController.java
package com.futvia.controller.partido;

import com.futvia.dto.partido.EstadioDTO;
import com.futvia.dto.partido.EstadioFormDTO;
import com.futvia.service.partido.EstadioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/estadios")
@RequiredArgsConstructor
public class EstadioController {

    private final EstadioService estadioService;

    // GET /api/estadios
    @GetMapping
    public ResponseEntity<Page<EstadioDTO>> listar(Pageable pageable) {
        return ResponseEntity.ok(estadioService.listar(pageable));
    }

    // GET /api/estadios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EstadioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estadioService.obtener(id));
    }

    // POST /api/estadios
    @PostMapping
    public ResponseEntity<EstadioDTO> crear(@Valid @RequestBody EstadioFormDTO form) {
        EstadioDTO dto = estadioService.crear(form);
        return ResponseEntity.created(URI.create("/api/estadios/" + dto.getId())).body(dto);
    }

    // PUT /api/estadios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EstadioDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstadioFormDTO form) {
        return ResponseEntity.ok(estadioService.actualizar(id, form));
    }

    // DELETE /api/estadios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
