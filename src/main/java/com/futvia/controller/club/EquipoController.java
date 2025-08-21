// src/main/java/com/futvia/controller/club/EquipoController.java
package com.futvia.controller.club;

import com.futvia.dto.club.EquipoDTO;
import com.futvia.dto.club.EquipoFormDTO;
import com.futvia.service.club.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;

    @GetMapping
    public Page<EquipoDTO> listar(
            @RequestParam(value = "competicionId", required = false) Long competicionId,
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @ParameterObject Pageable pageable
    ) {
        return service.listar(competicionId, categoriaId, pageable);
    }

    @GetMapping("/{id}")
    public EquipoDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipoDTO crear(@Valid @RequestBody EquipoFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public EquipoDTO editar(@PathVariable Long id, @Valid @RequestBody EquipoFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
