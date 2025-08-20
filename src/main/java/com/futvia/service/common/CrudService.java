// src/main/java/com/futvia/service/common/CrudService.java
package com.futvia.service.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<DTO, FORM> {
    Page<DTO> listar(Pageable pageable);
    DTO obtener(Long id);
    DTO crear(FORM form);
    DTO editar(Long id, FORM form);
    void eliminar(Long id);
}
