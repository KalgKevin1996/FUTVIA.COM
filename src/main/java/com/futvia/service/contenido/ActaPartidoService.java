// src/main/java/com/futvia/service/contenido/ActaPartidoService.java
package com.futvia.service.contenido;

import com.futvia.dto.contenido.ActaPartidoDTO;
import com.futvia.dto.contenido.ActaPartidoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActaPartidoService {
    Page<ActaPartidoDTO> listar(Pageable pageable);
    Optional<ActaPartidoDTO> obtenerPorPartido(Long partidoId);
    ActaPartidoDTO obtener(Long id);

    ActaPartidoDTO crear(ActaPartidoFormDTO form); // 1 acta por partido
    ActaPartidoDTO editar(Long id, ActaPartidoFormDTO form);
    void eliminar(Long id);
}
