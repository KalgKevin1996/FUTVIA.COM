package com.futvia.controller.disciplina;

import com.futvia.dto.common.PageResponseDTO;
import com.futvia.dto.disciplina.ReporteDisciplinarioDTO;
import com.futvia.dto.disciplina.ReporteDisciplinarioFormDTO;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import com.futvia.service.common.PageResponseFactory;
import com.futvia.service.common.PageUtils;
import com.futvia.service.disciplina.ReporteDisciplinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disciplinas/reportes")
@RequiredArgsConstructor
public class ReporteDisciplinarioController {

    private final ReporteDisciplinarioService service;

    // --------- Read ---------

    @GetMapping("/{id}")
    public ReporteDisciplinarioDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    /**
     * Listado flexible: filtra por partidoId, temporadaId, reportanteId o búsqueda por texto (q).
     * (Debes enviar al menos un filtro; si envías más de uno, se prioriza el orden partido -> temporada -> reportante -> q)
     */
    @GetMapping
    public ResponseEntity<PageResponseDTO<ReporteDisciplinarioDTO>> listar(
            @RequestParam(required = false) Long partidoId,
            @RequestParam(required = false) Long temporadaId,
            @RequestParam(required = false) Long reportanteId,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ) {
        Pageable pageable = PageUtils.of(page, size, sort);
        Page<ReporteDisciplinarioDTO> result;

        if (partidoId != null) {
            result = service.listarPorPartido(partidoId, pageable);
        } else if (temporadaId != null) {
            result = service.listarPorTemporada(temporadaId, pageable);
        } else if (reportanteId != null) {
            result = service.listarPorReportante(reportanteId, pageable);
        } else if (q != null && !q.isBlank()) {
            result = service.buscar(q, pageable);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(PageResponseFactory.from(result));
    }

    // --------- Write ---------

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReporteDisciplinarioDTO crear(@Validated @RequestBody ReporteDisciplinarioFormDTO form) {
        return service.crear(form);
    }

    @PutMapping("/{id}")
    public ReporteDisciplinarioDTO actualizar(@PathVariable Long id,
                                              @Validated @RequestBody ReporteDisciplinarioFormDTO form) {
        return service.actualizar(id, form);
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
