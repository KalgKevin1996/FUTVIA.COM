// src/main/java/com/futvia/service/competicion/OrganizadorService.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.OrganizadorDTO;
import com.futvia.dto.competicion.OrganizadorFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizadorService {
    Page<OrganizadorDTO> listar(String q, String tipo, Pageable pageable);
    OrganizadorDTO obtener(Long id);
    OrganizadorDTO crear(OrganizadorFormDTO form);
    OrganizadorDTO editar(Long id, OrganizadorFormDTO form);
    void eliminar(Long id);
}
