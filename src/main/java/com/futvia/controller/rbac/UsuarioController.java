package com.futvia.controller.rbac;

import com.futvia.dto.rbac.UsuarioDTO;
import com.futvia.dto.rbac.UsuarioFormDTO;
import com.futvia.service.rbac.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rbac/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public Page<UsuarioDTO> listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Boolean estado,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return service.listar(q, estado, pageable);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestParam String email) {
        Optional<UsuarioDTO> opt = service.buscarPorEmail(email);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public UsuarioDTO editar(@PathVariable Long id, @Valid @RequestBody UsuarioFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
