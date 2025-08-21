package com.futvia.controller.rbac;

import com.futvia.dto.rbac.RolPermisoDTO;
import com.futvia.service.rbac.RolPermisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rbac/rol-permiso")
@RequiredArgsConstructor
public class RolPermisoController {

    private final RolPermisoService service;

    @GetMapping
    public Page<RolPermisoDTO> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/rol/{rolId}")
    public List<RolPermisoDTO> listarPorRol(@PathVariable Long rolId) {
        return service.listarPorRol(rolId);
    }

    @GetMapping("/permiso/{permisoId}")
    public List<RolPermisoDTO> listarPorPermiso(@PathVariable Long permisoId) {
        return service.listarPorPermiso(permisoId);
    }

    @PostMapping("/rol/{rolId}/permiso/{permisoId}")
    public ResponseEntity<Void> asignar(@PathVariable Long rolId, @PathVariable Long permisoId) {
        service.asignar(rolId, permisoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rol/{rolId}/permiso/{permisoId}")
    public ResponseEntity<Void> revocar(@PathVariable Long rolId, @PathVariable Long permisoId) {
        service.revocar(rolId, permisoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rol/{rolId}")
    public ResponseEntity<Void> limpiarPorRol(@PathVariable Long rolId) {
        service.limpiarPorRol(rolId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/permiso/{permisoId}")
    public ResponseEntity<Void> limpiarPorPermiso(@PathVariable Long permisoId) {
        service.limpiarPorPermiso(permisoId);
        return ResponseEntity.noContent().build();
    }
}
