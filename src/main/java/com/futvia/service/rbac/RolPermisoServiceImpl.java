// src/main/java/com/futvia/service/rbac/RolPermisoServiceImpl.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.RolPermisoDTO;
import com.futvia.mapper.rbac.RolPermisoMapper;
import com.futvia.model.rbac.Permiso;
import com.futvia.model.rbac.Rol;
import com.futvia.model.rbac.RolPermiso;
import com.futvia.repository.rbac.RolPermisoRepository;
import com.futvia.service.common.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolPermisoServiceImpl implements RolPermisoService {

    private final RolPermisoRepository repo;
    private final RolPermisoMapper mapper;

    @Override
    public Page<RolPermisoDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public List<RolPermisoDTO> listarPorRol(Long rolId) {
        return repo.findByRol_Id(rolId).stream().map(mapper::toDto).toList();
    }

    @Override
    public List<RolPermisoDTO> listarPorPermiso(Long permisoId) {
        return repo.findByPermiso_Id(permisoId).stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public void asignar(Long rolId, Long permisoId) {
        if (repo.existsByRol_IdAndPermiso_Id(rolId, permisoId)) {
            throw new BusinessException("El rol ya tiene asignado ese permiso.");
        }
        RolPermiso rp = new RolPermiso();
        Rol rol = new Rol(); rol.setId(rolId); rp.setRol(rol);
        Permiso permiso = new Permiso(); permiso.setId(permisoId); rp.setPermiso(permiso);
        repo.save(rp);
    }

    @Override
    @Transactional
    public void revocar(Long rolId, Long permisoId) {
        repo.findByRol_IdAndPermiso_Id(rolId, permisoId)
                .ifPresent(repo::delete);
        // Si prefieres error cuando no existe, cambia a:
        // .orElseThrow(() -> new NotFoundException("No existe esa asignación rol-permiso"));
    }

    @Override
    @Transactional
    public void limpiarPorRol(Long rolId) {
        repo.deleteByRol_Id(rolId);
    }

    @Override
    @Transactional
    public void limpiarPorPermiso(Long permisoId) {
        repo.deleteByPermiso_Id(permisoId);
    }
}
