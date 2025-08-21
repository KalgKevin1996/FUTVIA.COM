// src/main/java/com/futvia/service/rbac/PermisoService.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.PermisoDTO;
import com.futvia.dto.rbac.PermisoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermisoService {
    Page<PermisoDTO> listar(String q, Pageable pageable);
    PermisoDTO obtener(Long id);
    PermisoDTO crear(PermisoFormDTO form);
    PermisoDTO editar(Long id, PermisoFormDTO form);
    void eliminar(Long id);
}
