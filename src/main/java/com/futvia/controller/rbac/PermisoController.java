package com.futvia.controller.rbac;

import com.futvia.dto.rbac.PermisoDTO;
import com.futvia.dto.rbac.PermisoFormDTO;
import com.futvia.service.rbac.PermisoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rbac/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService service;

    @GetMapping
    public Page<PermisoDTO> listar(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return service.listar(q, pageable);
    }

    @GetMapping("/{id}")
    public PermisoDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<PermisoDTO> crear(@Valid @RequestBody PermisoFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public PermisoDTO editar(@PathVariable Long id, @Valid @RequestBody PermisoFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
