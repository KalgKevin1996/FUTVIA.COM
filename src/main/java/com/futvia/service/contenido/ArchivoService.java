// src/main/java/com/futvia/service/contenido/ArchivoService.java
package com.futvia.service.contenido;

import com.futvia.dto.contenido.ArchivoDTO;
import com.futvia.dto.contenido.ArchivoFormDTO;
import com.futvia.model.common.enums.ArchivoTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArchivoService {
    Page<ArchivoDTO> listar(Pageable pageable);
    Page<ArchivoDTO> listarPorTipo(ArchivoTipo tipo, Pageable pageable);
    Page<ArchivoDTO> buscarPorCreador(String creadoPor, Pageable pageable);

    ArchivoDTO obtener(Long id);
    ArchivoDTO crear(ArchivoFormDTO form);   // valida s3Key única
    ArchivoDTO editar(Long id, ArchivoFormDTO form);
    void eliminar(Long id);
}
