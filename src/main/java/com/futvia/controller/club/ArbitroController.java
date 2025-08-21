// src/main/java/com/futvia/controller/club/ArbitroController.java
package com.futvia.controller.club;

import com.futvia.dto.club.ArbitroDTO;
import com.futvia.dto.club.ArbitroFormDTO;
import com.futvia.service.club.ArbitroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/arbitros")
@RequiredArgsConstructor
public class ArbitroController {

    private final ArbitroService service;

    @GetMapping
    public Page<ArbitroDTO> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ArbitroDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArbitroDTO crear(@Valid @RequestBody ArbitroFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public ArbitroDTO editar(@PathVariable Long id, @Valid @RequestBody ArbitroFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
