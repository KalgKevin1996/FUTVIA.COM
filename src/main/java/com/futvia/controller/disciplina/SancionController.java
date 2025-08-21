package com.futvia.controller.disciplina;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.disciplina.SancionDTO;
import com.futvia.dto.disciplina.SancionFormDTO;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.disciplina.SancionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/disciplinas/sanciones")
@RequiredArgsConstructor
public class SancionController {

    private final SancionService service;

    // --------- Read ---------

    @GetMapping("/{id}")
    public SancionDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Listado flexible de sanciones.
     * Admite: jugadorId, equipoId, reporteId, activasEn (fecha), o rango [desde, hasta].
     * Debe enviarse al menos un filtro. Si se envían varios, se prioriza:
     * jugadorId -> equipoId -> reporteId -> activasEn -> rango.
     */
    @GetMapping
    public ResponseEntity<PageResponseDTO<SancionDTO>> listar(
            @RequestParam(required = false) Long jugadorId,
            @RequestParam(required = false) Long equipoId,
            @RequestParam(required = false) Long reporteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate activasEn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageUtils.of(page, size, sort);
        Page<SancionDTO> result;

        if (jugadorId != null) {
            result = service.listarPorJugador(jugadorId, pageable);
        } else if (equipoId != null) {
            result = service.listarPorEquipo(equipoId, pageable);
        } else if (reporteId != null) {
            result = service.listarPorReporte(reporteId, pageable);
        } else if (activasEn != null) {
            result = service.listarActivas(activasEn, pageable);
        } else if (desde != null && hasta != null) {
            result = service.listarPorRango(desde, hasta, pageable);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(PageResponseFactory.from(result));
    }

    /**
     * Atajos para obtener sanciones activas en una fecha para un jugador o equipo.
     * Debes enviar jugadorId o equipoId (no ambos).
     */
    @GetMapping("/activas")
    public ResponseEntity<List<SancionDTO>> activas(
            @RequestParam(required = false) Long jugadorId,
            @RequestParam(required = false) Long equipoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        if ((jugadorId == null) == (equipoId == null)) {
            return ResponseEntity.badRequest().build();
        }
        if (jugadorId != null) {
            return ResponseEntity.ok(service.activasDeJugador(jugadorId, fecha));
        } else {
            return ResponseEntity.ok(service.activasDeEquipo(equipoId, fecha));
        }
    }

    // --------- Write ---------

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SancionDTO crear(@Validated @RequestBody SancionFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public SancionDTO actualizar(@PathVariable Long id,
                                 @Validated @RequestBody SancionFormDTO form) {
        return service.actualizar(id, form);
    }

    /**
     * Cierra una sanción fijando su fecha fin inclusiva.
     */
    @PatchMapping("/{id}/cierre")
    public SancionDTO cerrar(@PathVariable Long id,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return service.cerrar(id, fin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // --------- Error mapping ---------

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusiness(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }
}
