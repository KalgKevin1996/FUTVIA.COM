// src/main/java/com/futvia/controller/partido/TernaArbitralController.java
package com.futvia.controller.partido;

import com.futvia.dto.partido.TernaArbitralDTO;
import com.futvia.dto.partido.TernaDetalleDTO;
import com.futvia.model.common.enums.RolArbitral;
import com.futvia.service.partido.TernaArbitralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ternas")
@RequiredArgsConstructor
public class TernaArbitralController {

    private final TernaArbitralService ternaArbitralService;

    // POST /api/ternas/partido/{partidoId}
    @PostMapping("/partido/{partidoId}")
    public ResponseEntity<TernaArbitralDTO> crearParaPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(ternaArbitralService.crearParaPartido(partidoId));
    }

    // GET /api/ternas/partido/{partidoId}
    @GetMapping("/partido/{partidoId}")
    public ResponseEntity<TernaArbitralDTO> obtenerPorPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(ternaArbitralService.obtenerPorPartido(partidoId));
    }

    // POST /api/ternas/{ternaId}/agregar-arbitro?arbitroId=&rol=
    @PostMapping("/{ternaId}/agregar-arbitro")
    public ResponseEntity<TernaDetalleDTO> agregarArbitro(
            @PathVariable Long ternaId,
            @RequestParam Long arbitroId,
            @RequestParam RolArbitral rol) {
        return ResponseEntity.ok(ternaArbitralService.agregarArbitro(ternaId, arbitroId, rol));
    }

    // DELETE /api/ternas/detalles/{ternaDetalleId}
    @DeleteMapping("/detalles/{ternaDetalleId}")
    public ResponseEntity<Void> quitarDetalle(@PathVariable Long ternaDetalleId) {
        ternaArbitralService.quitarDetalle(ternaDetalleId);
        return ResponseEntity.noContent().build();
    }
}
