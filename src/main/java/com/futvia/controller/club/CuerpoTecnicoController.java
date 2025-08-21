// src/main/java/com/futvia/controller/club/CuerpoTecnicoController.java
package com.futvia.controller.club;

import com.futvia.dto.club.CuerpoTecnicoDTO;
import com.futvia.dto.club.CuerpoTecnicoFormDTO;
import com.futvia.service.club.CuerpoTecnicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuerpos-tecnicos")
@RequiredArgsConstructor
public class CuerpoTecnicoController {

    private final CuerpoTecnicoService service;

    @GetMapping
    public Page<CuerpoTecnicoDTO> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/equipo/{equipoId}")
    public List<CuerpoTecnicoDTO> listarPorEquipo(@PathVariable Long equipoId) {
        return service.listarPorEquipo(equipoId);
    }

    @GetMapping("/{id}")
    public CuerpoTecnicoDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuerpoTecnicoDTO crear(@Valid @RequestBody CuerpoTecnicoFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public CuerpoTecnicoDTO editar(@PathVariable Long id, @Valid @RequestBody CuerpoTecnicoFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
