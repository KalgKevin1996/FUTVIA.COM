package com.futvia.controller.geo;

import com.futvia.dto.geo.ZonaDTO;
import com.futvia.dto.geo.ZonaFormDTO;
import com.futvia.service.geo.ZonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo/zonas")
@RequiredArgsConstructor
public class ZonaController {

    private final ZonaService service;

    @GetMapping
    public ResponseEntity<Page<ZonaDTO>> listar(
            @RequestParam(required = false) Long municipioId,
            @RequestParam(required = false) Integer numero,
            @PageableDefault(size = 20, sort = "numero") Pageable pageable) {
        return ResponseEntity.ok(service.listar(municipioId, numero, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<ZonaDTO> crear(@Valid @RequestBody ZonaFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaDTO> editar(@PathVariable Long id, @Valid @RequestBody ZonaFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}