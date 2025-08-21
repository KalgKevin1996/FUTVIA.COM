package com.futvia.controller.rbac;

import com.futvia.dto.rbac.AsignacionRolDTO;
import com.futvia.dto.rbac.AsignacionRolFormDTO;
import com.futvia.model.common.enums.RolNombre;
import com.futvia.service.rbac.AsignacionRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rbac/asignaciones")
@RequiredArgsConstructor
public class AsignacionRolController {

    private final AsignacionRolService service;

    @GetMapping
    public Page<AsignacionRolDTO> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AsignacionRolDTO> listarDeUsuario(@PathVariable Long usuarioId) {
        return service.listarDeUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/rol")
    public List<AsignacionRolDTO> listarDeUsuarioPorRol(
            @PathVariable Long usuarioId,
            @RequestParam RolNombre rol
    ) {
        return service.listarDeUsuarioPorRol(usuarioId, rol);
    }

    @GetMapping("/{id}")
    public AsignacionRolDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<AsignacionRolDTO> asignar(@Valid @RequestBody AsignacionRolFormDTO form) {
        return ResponseEntity.ok(service.asignar(form));
    }

    @PutMapping("/{id}")
    public AsignacionRolDTO editar(@PathVariable Long id, @Valid @RequestBody AsignacionRolFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revocar(@PathVariable Long id) {
        service.revocar(id);
        return ResponseEntity.noContent().build();
    }
}
