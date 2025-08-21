// src/main/java/com/futvia/service/partido/JornadaService.java
package com.futvia.service.partido;

import com.futvia.dto.partido.JornadaDTO;
import com.futvia.dto.partido.JornadaFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface JornadaService {

    Page<JornadaDTO> listarPorTemporada(Long temporadaId, Pageable pageable);

    JornadaDTO obtener(Long id);

    /**
     * Busca una jornada por (temporadaId, numero).
     */
    Optional<JornadaDTO> buscarPorNumero(Long temporadaId, Integer numero);

    /**
     * Lista jornadas cuyo fechaInicio esté en [desde, hasta].
     */
    Page<JornadaDTO> listarRangoFechas(Long temporadaId, LocalDate desde, LocalDate hasta, Pageable pageable);

    JornadaDTO crear(JornadaFormDTO form);

    JornadaDTO actualizar(Long id, JornadaFormDTO form);

    void eliminar(Long id);
}
