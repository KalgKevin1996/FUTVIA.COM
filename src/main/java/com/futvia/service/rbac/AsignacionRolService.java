package com.futvia.service.rbac;


import com.futvia.dto.rbac.*;
import org.springframework.data.domain.*;


public interface AsignacionRolService {
    Page<AsignacionRolDTO> listarPorUsuario(Long usuarioId, Pageable pageable);
    AsignacionRolDTO asignar(AsignacionRolFormDTO form);
    void revocar(Long asignacionId);
}