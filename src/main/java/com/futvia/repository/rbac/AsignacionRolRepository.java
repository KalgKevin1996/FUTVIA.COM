package com.futvia.repository.rbac;

import com.futvia.model.rbac.AsignacionRol;
import com.futvia.model.common.enums.AmbitoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignacionRolRepository extends JpaRepository<AsignacionRol, Long> {
    List<AsignacionRol> findByUsuario_Id(Long usuarioId);
    List<AsignacionRol> findByUsuario_IdAndRol_Nombre(Long usuarioId, com.futvia.model.common.enums.RolNombre rol);
    List<AsignacionRol> findByUsuario_IdAndRol_NombreAndAmbitoTipoAndAmbitoId(Long usuarioId, com.futvia.model.common.enums.RolNombre rol, AmbitoTipo ambitoTipo, Long ambitoId);
}