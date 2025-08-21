// src/main/java/com/futvia/service/rbac/RolPermisoService.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.RolPermisoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolPermisoService {
    Page<RolPermisoDTO> listar(Pageable pageable);
    List<RolPermisoDTO> listarPorRol(Long rolId);
    List<RolPermisoDTO> listarPorPermiso(Long permisoId);

    void asignar(Long rolId, Long permisoId);
    void revocar(Long rolId, Long permisoId);

    void limpiarPorRol(Long rolId);
    void limpiarPorPermiso(Long permisoId);
}
