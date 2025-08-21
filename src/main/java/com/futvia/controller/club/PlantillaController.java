// src/main/java/com/futvia/controller/club/PlantillaController.java
package com.futvia.controller.club;

import com.futvia.dto.club.PlantillaDTO;
import com.futvia.dto.club.PlantillaFormDTO;
import com.futvia.service.club.PlantillaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plantillas")
@RequiredArgsConstructor
public class PlantillaController {

    private final PlantillaService service;

    @GetMapping
    public Page<PlantillaDTO> listar(
            @RequestParam(value = "equipoId", required = false) Long equipoId,
            @RequestParam(value = "temporadaId", required = false) Long temporadaId,
            @ParameterObject Pageable pageable
    ) {
        return service.listar(equipoId, temporadaId, pageable);
    }

    @GetMapping("/{id}")
    public PlantillaDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlantillaDTO agregar(@Valid @RequestBody PlantillaFormDTO form) {
        return service.agregar(form);
    }

    @PutMapping("/{id}")
    public PlantillaDTO editar(@PathVariable Long id, @Valid @RequestBody PlantillaFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
