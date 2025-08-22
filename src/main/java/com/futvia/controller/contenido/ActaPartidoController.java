// src/main/java/com/futvia/controller/contenido/ActaPartidoController.java
package com.futvia.controller.contenido;

import com.futvia.dto.contenido.ActaPartidoDTO;
import com.futvia.dto.contenido.ActaPartidoFormDTO;
import com.futvia.service.contenido.ActaPartidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/contenido/actas")
@RequiredArgsConstructor
public class ActaPartidoController {
    private final ActaPartidoService service;

    @GetMapping
    public Page<ActaPartidoDTO> listar(@PageableDefault(size = 20) Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/por-partido/{partidoId}")
    public ResponseEntity<ActaPartidoDTO> obtenerPorPartido(@PathVariable Long partidoId) {
        Optional<ActaPartidoDTO> dto = service.obtenerPorPartido(partidoId);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ActaPartidoDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<ActaPartidoDTO> crear(@Valid @RequestBody ActaPartidoFormDTO form) {
        var dto = service.crear(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ActaPartidoDTO editar(@PathVariable Long id, @Valid @RequestBody ActaPartidoFormDTO form) {
        return service.editar(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
