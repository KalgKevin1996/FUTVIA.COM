package com.futvia.controller.geo;

import com.futvia.dto.geo.MunicipioDTO;
import com.futvia.dto.geo.MunicipioFormDTO;
import com.futvia.service.geo.MunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo/municipios")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService service;

    @GetMapping
    public ResponseEntity<Page<MunicipioDTO>> listar(
            @RequestParam(required = false) Long departamentoId,
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20, sort = "nombre") Pageable pageable) {
        return ResponseEntity.ok(service.listar(departamentoId, q, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<MunicipioDTO> crear(@Valid @RequestBody MunicipioFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipioDTO> editar(@PathVariable Long id, @Valid @RequestBody MunicipioFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}