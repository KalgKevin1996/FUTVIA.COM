package com.futvia.service.rbac;


import com.futvia.dto.rbac.*;
import com.futvia.service.common.CrudSupport;
import org.springframework.data.domain.*;


public interface UsuarioService extends CrudSupport<UsuarioDTO, UsuarioFormDTO> {
    Page<UsuarioDTO> buscar(String q, Pageable pageable);
    UsuarioDTO buscarPorEmail(String email);
    void activar(Long id, boolean estado);
}