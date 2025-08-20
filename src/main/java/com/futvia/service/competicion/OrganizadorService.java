package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import org.springframework.data.domain.*;

public interface OrganizadorService {
    Page<OrganizadorDTO> listar(Pageable p);
    OrganizadorDTO crear(OrganizadorFormDTO f);
    OrganizadorDTO editar(Long id, OrganizadorFormDTO f);
    void eliminar(Long id);
}
