package com.futvia.controller.rbac;

import com.futvia.dto.rbac.RolDTO;
import com.futvia.dto.rbac.RolFormDTO;
import com.futvia.service.rbac.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rbac/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService service;

    @GetMapping
    public Page<RolDTO> listar(
            @RequestParam(required = false) Integer nivelMin,
            @RequestParam(required = false) Integer nivelMax,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return service.listar(nivelMin, nivelMax, pageable);
    }

    @GetMapping("/{id}")
    public RolDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<RolDTO> crear(@Valid @RequestBody RolFormDTO form) {
        RolDTO dto = service.crear(form);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public RolDTO editar(@PathVariable Long id, @Valid @RequestBody RolFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
