// src/main/java/com/futvia/service/rbac/UsuarioService.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.UsuarioDTO;
import com.futvia.dto.rbac.UsuarioFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {
    Page<UsuarioDTO> listar(String q, Boolean estado, Pageable pageable);
    Optional<UsuarioDTO> buscarPorEmail(String email);
    UsuarioDTO obtener(Long id);
    UsuarioDTO crear(UsuarioFormDTO form);
    UsuarioDTO editar(Long id, UsuarioFormDTO form);
    void eliminar(Long id);
}
