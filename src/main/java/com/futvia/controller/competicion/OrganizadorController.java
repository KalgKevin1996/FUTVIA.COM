// src/main/java/com/futvia/controller/competicion/OrganizadorController.java
package com.futvia.controller.competicion;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.competicion.OrganizadorDTO;
import com.futvia.dto.competicion.OrganizadorFormDTO;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.competicion.OrganizadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competicion/organizadores")
@RequiredArgsConstructor
@Validated
public class OrganizadorController {

    private final OrganizadorService service;

    @GetMapping
    public ResponseEntity<PageResponseDTO<OrganizadorDTO>> listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String tipo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageUtils.of(page, size, sort);
        var result = service.listar(q, tipo, pageable);
        return ResponseEntity.ok(PageResponseFactory.from(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<OrganizadorDTO> crear(@Valid @RequestBody OrganizadorFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> editar(@PathVariable Long id, @Valid @RequestBody OrganizadorFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
