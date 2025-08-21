// src/main/java/com/futvia/controller/competicion/CompeticionController.java
package com.futvia.controller.competicion;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.competicion.CompeticionDTO;
import com.futvia.dto.competicion.CompeticionFormDTO;
import com.futvia.model.common.enums.TipoCompeticion;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.competicion.CompeticionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competicion/competiciones")
@RequiredArgsConstructor
@Validated
public class CompeticionController {

    private final CompeticionService service;

    @GetMapping
    public ResponseEntity<PageResponseDTO<CompeticionDTO>> listar(
            @RequestParam(required = false) Long organizadorId,
            @RequestParam(required = false) TipoCompeticion tipo,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageUtils.of(page, size, sort);
        var result = service.listar(organizadorId, tipo, q, pageable);
        return ResponseEntity.ok(PageResponseFactory.from(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompeticionDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<CompeticionDTO> crear(@Valid @RequestBody CompeticionFormDTO form) {
        return ResponseEntity.ok(service.crear(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompeticionDTO> editar(@PathVariable Long id, @Valid @RequestBody CompeticionFormDTO form) {
        return ResponseEntity.ok(service.editar(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
