package com.futvia.controller.geo;

import com.futvia.dto.geo.PaisDTO;
import com.futvia.dto.geo.PaisFormDTO;
import com.futvia.service.geo.PaisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo/paises")
@RequiredArgsConstructor
public class PaisController {

    private final PaisService service;

    @GetMapping
    public ResponseEntity<Page<PaisDTO>> listar(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(service.listar(q, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<PaisDTO> crear(@Valid @RequestBody PaisFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> editar(@PathVariable Long id, @Valid @RequestBody PaisFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}