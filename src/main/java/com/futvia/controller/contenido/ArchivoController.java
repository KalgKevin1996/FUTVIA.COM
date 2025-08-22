// src/main/java/com/futvia/controller/contenido/ArchivoController.java
package com.futvia.controller.contenido;

import com.futvia.dto.contenido.ArchivoDTO;
import com.futvia.dto.contenido.ArchivoFormDTO;
import com.futvia.model.common.enums.ArchivoTipo;
import com.futvia.service.contenido.ArchivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contenido/archivos")
@RequiredArgsConstructor
public class ArchivoController {
    private final ArchivoService service;

    @GetMapping
    public Page<ArchivoDTO> listar(@PageableDefault(size = 20) Pageable pageable,
                                   @RequestParam(value = "tipo", required = false) ArchivoTipo tipo,
                                   @RequestParam(value = "creadoPor", required = false) String creadoPor) {
        if (tipo != null) {
            return service.listarPorTipo(tipo, pageable);
        }
        if (creadoPor != null && !creadoPor.isBlank()) {
            return service.buscarPorCreador(creadoPor, pageable);
        }
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ArchivoDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<ArchivoDTO> crear(@Valid @RequestBody ArchivoFormDTO form) {
        var dto = service.crear(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ArchivoDTO editar(@PathVariable Long id, @Valid @RequestBody ArchivoFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
