// src/main/java/com/futvia/controller/competicion/CategoriaController.java
package com.futvia.controller.competicion;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.competicion.CategoriaDTO;
import com.futvia.dto.competicion.CategoriaFormDTO;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.competicion.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competicion/categorias")
@RequiredArgsConstructor
@Validated
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public ResponseEntity<PageResponseDTO<CategoriaDTO>> listar(
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
    public ResponseEntity<CategoriaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody CategoriaFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> editar(@PathVariable Long id, @Valid @RequestBody CategoriaFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
