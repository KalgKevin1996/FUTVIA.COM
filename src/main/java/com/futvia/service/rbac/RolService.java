// src/main/java/com/futvia/service/rbac/RolService.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.RolDTO;
import com.futvia.dto.rbac.RolFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolService {
    Page<RolDTO> listar(Integer nivelMin, Integer nivelMax, Pageable pageable);
    RolDTO obtener(Long id);
    RolDTO crear(RolFormDTO form);
    RolDTO editar(Long id, RolFormDTO form);
    void eliminar(Long id);
}
