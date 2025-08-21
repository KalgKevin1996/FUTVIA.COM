// src/main/java/com/futvia/controller/club/JugadorController.java
package com.futvia.controller.club;

import com.futvia.dto.club.JugadorDTO;
import com.futvia.dto.club.JugadorFormDTO;
import com.futvia.service.club.JugadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService service;

    @GetMapping
    public Page<JugadorDTO> buscar(
            @RequestParam(value = "q", required = false) String q,
            @ParameterObject Pageable pageable
    ) {
        return service.buscar(q, pageable);
    }

    @GetMapping("/{id}")
    public JugadorDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JugadorDTO crear(@Valid @RequestBody JugadorFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public JugadorDTO editar(@PathVariable Long id, @Valid @RequestBody JugadorFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
