package com.futvia.controller.geo;

import com.futvia.dto.geo.DepartamentoDTO;
import com.futvia.dto.geo.DepartamentoFormDTO;
import com.futvia.service.geo.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService service;

    @GetMapping
    public ResponseEntity<Page<DepartamentoDTO>> listar(
            @RequestParam(required = false) Long paisId,
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(service.listar(paisId, q, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> crear(@Valid @RequestBody DepartamentoFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> editar(@PathVariable Long id, @Valid @RequestBody DepartamentoFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}