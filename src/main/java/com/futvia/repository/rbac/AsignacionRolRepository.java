// src/main/java/com/futvia/repository/rbac/AsignacionRolRepository.java
package com.futvia.repository.rbac;

import com.futvia.model.common.enums.AmbitoTipo;
import com.futvia.model.common.enums.RolNombre;
import com.futvia.model.rbac.AsignacionRol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionRolRepository extends JpaRepository<AsignacionRol, Long> {
    // Búsquedas principales por usuario/rol/ámbito
    List<AsignacionRol> findByUsuario_Id(Long usuarioId);
    List<AsignacionRol> findByUsuario_IdAndRol_Nombre(Long usuarioId, RolNombre rol); // rol.nombre enum :contentReference[oaicite:9]{index=9} :contentReference[oaicite:10]{index=10}
    List<AsignacionRol> findByUsuario_IdAndRol_NombreAndAmbitoTipoAndAmbitoId(
            Long usuarioId, RolNombre rol, AmbitoTipo ambitoTipo, Long ambitoId);      // unique compuesto :contentReference[oaicite:11]{index=11}

    // Variantes paginadas para vistas administrativas
    Page<AsignacionRol> findByRol_Nombre(RolNombre rol, Pageable pageable);
    Page<AsignacionRol> findByAmbitoTipoAndAmbitoId(AmbitoTipo ambitoTipo, Long ambitoId, Pageable pageable);

    // Validaciones/exists según unique (usuario, rol, ambitoTipo, ambitoId)
    boolean existsByUsuario_IdAndRol_IdAndAmbitoTipoAndAmbitoId(
            Long usuarioId, Long rolId, AmbitoTipo ambitoTipo, Long ambitoId);

    // Limpiezas controladas por usuario o por ámbito (útil al borrar una liga/temporada/equipo)
    long deleteByUsuario_Id(Long usuarioId);
    long deleteByAmbitoTipoAndAmbitoId(AmbitoTipo ambitoTipo, Long ambitoId);
}
