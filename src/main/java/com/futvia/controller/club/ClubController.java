// src/main/java/com/futvia/controller/club/ClubController.java
package com.futvia.controller.club;

import com.futvia.dto.club.ClubDTO;
import com.futvia.dto.club.ClubFormDTO;
import com.futvia.service.club.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubes")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService service;

    @GetMapping
    public Page<ClubDTO> listar(
            @RequestParam(value = "q", required = false) String q,
            @ParameterObject Pageable pageable
    ) {
        return service.listar(q, pageable);
    }

    @GetMapping("/{id}")
    public ClubDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDTO crear(@Valid @RequestBody ClubFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public ClubDTO editar(@PathVariable Long id, @Valid @RequestBody ClubFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
