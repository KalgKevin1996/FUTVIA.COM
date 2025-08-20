package com.futvia.repository.rbac;

import com.futvia.model.rbac.AsignacionRol;
import com.futvia.model.common.enums.AmbitoTipo;
import com.futvia.model.common.enums.RolNombre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionRolRepository extends JpaRepository<AsignacionRol, Long> {
    // NUEVO: paginado
    Page<AsignacionRol> findByUsuario_Id(Long usuarioId, Pageable pageable);

    // (si aún los usas)
    List<AsignacionRol> findByUsuario_Id(Long usuarioId);
    List<AsignacionRol> findByUsuario_IdAndRol_Nombre(Long usuarioId, RolNombre rol);
    List<AsignacionRol> findByUsuario_IdAndRol_NombreAndAmbitoTipoAndAmbitoId(Long usuarioId, RolNombre rol, AmbitoTipo ambitoTipo, Long ambitoId);
}
