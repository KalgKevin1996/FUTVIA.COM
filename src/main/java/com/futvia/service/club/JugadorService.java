// src/main/java/com/futvia/service/club/JugadorService.java
package com.futvia.service.club;

import com.futvia.dto.club.JugadorDTO;
import com.futvia.dto.club.JugadorFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JugadorService {
    Page<JugadorDTO> buscar(String q, Pageable pageable); // por nombre/apellido
    JugadorDTO obtener(Long id);
    JugadorDTO crear(JugadorFormDTO form);
    JugadorDTO editar(Long id, JugadorFormDTO form);
    void eliminar(Long id);
}
