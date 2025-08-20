// src/main/java/com/futvia/repository/rbac/RolPermisoRepository.java
package com.futvia.repository.rbac;

import com.futvia.model.rbac.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long> {
    // Unique (rol_id, permiso_id) para validar antes de crear :contentReference[oaicite:8]{index=8}
    Optional<RolPermiso> findByRol_IdAndPermiso_Id(Long rolId, Long permisoId);
    boolean existsByRol_IdAndPermiso_Id(Long rolId, Long permisoId);

    // Listados
    List<RolPermiso> findByRol_Id(Long rolId);
    List<RolPermiso> findByPermiso_Id(Long permisoId);

    // Mantenimiento por rol o permiso
    long deleteByRol_Id(Long rolId);
    long deleteByPermiso_Id(Long permisoId);
}
