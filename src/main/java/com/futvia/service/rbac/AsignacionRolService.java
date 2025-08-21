// src/main/java/com/futvia/service/rbac/AsignacionRolService.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.AsignacionRolDTO;
import com.futvia.dto.rbac.AsignacionRolFormDTO;
import com.futvia.model.common.enums.AmbitoTipo;
import com.futvia.model.common.enums.RolNombre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AsignacionRolService {
    Page<AsignacionRolDTO> listar(Pageable pageable);
    List<AsignacionRolDTO> listarDeUsuario(Long usuarioId);
    List<AsignacionRolDTO> listarDeUsuarioPorRol(Long usuarioId, RolNombre rol);
    AsignacionRolDTO obtener(Long id);
    AsignacionRolDTO asignar(AsignacionRolFormDTO form);
    AsignacionRolDTO editar(Long id, AsignacionRolFormDTO form);
    void revocar(Long id);
    boolean tieneAsignacion(Long usuarioId, Long rolId, AmbitoTipo ambitoTipo, Long ambitoId);
}
