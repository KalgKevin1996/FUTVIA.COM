// src/main/java/com/futvia/controller/competicion/TemporadaController.java
package com.futvia.controller.competicion;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.competicion.TemporadaDTO;
import com.futvia.dto.competicion.TemporadaFormDTO;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.competicion.TemporadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competicion/temporadas")
@RequiredArgsConstructor
@Validated
public class TemporadaController {

    private final TemporadaService service;

    @GetMapping
    public ResponseEntity<PageResponseDTO<TemporadaDTO>> listar(
            @RequestParam(required = false) Long competicionId,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageUtils.of(page, size, sort);
        var result = service.listar(competicionId, q, pageable);
        return ResponseEntity.ok(PageResponseFactory.from(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemporadaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<TemporadaDTO> crear(@Valid @RequestBody TemporadaFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemporadaDTO> editar(@PathVariable Long id, @Valid @RequestBody TemporadaFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
