// src/main/java/com/futvia/service/rbac/AsignacionRolServiceImpl.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.AsignacionRolDTO;
import com.futvia.dto.rbac.AsignacionRolFormDTO;
import com.futvia.mapper.rbac.AsignacionRolMapper;
import com.futvia.model.rbac.AsignacionRol;
import com.futvia.repository.rbac.AsignacionRolRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AsignacionRolServiceImpl implements AsignacionRolService {
    private final AsignacionRolRepository repo;
    private final AsignacionRolMapper mapper;

    public Page<AsignacionRolDTO> listar(Pageable pageable){
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public java.util.List<AsignacionRolDTO> listarDeUsuario(Long usuarioId){
        return repo.findByUsuario_Id(usuarioId).stream().map(mapper::toDto).toList();
    }

    public java.util.List<AsignacionRolDTO> listarDeUsuarioPorRol(Long usuarioId, com.futvia.model.common.enums.RolNombre rol){
        return repo.findByUsuario_IdAndRol_Nombre(usuarioId, rol).stream().map(mapper::toDto).toList();
    }

    public AsignacionRolDTO obtener(Long id){
        AsignacionRol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Asignación no encontrada: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public AsignacionRolDTO asignar(AsignacionRolFormDTO form){
        boolean existe = repo.existsByUsuario_IdAndRol_IdAndAmbitoTipoAndAmbitoId(
                form.getUsuarioId(), form.getRolId(), form.getAmbitoTipo(), form.getAmbitoId());
        if (existe) throw new BusinessException("La asignación ya existe para ese ámbito.");
        AsignacionRol e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public AsignacionRolDTO editar(Long id, AsignacionRolFormDTO form){
        AsignacionRol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Asignación no encontrada: " + id));
        // Si cambia la combinación, validar unicidad
        if (!(e.getUsuario().getId().equals(form.getUsuarioId())
                && e.getRol().getId().equals(form.getRolId())
                && java.util.Objects.equals(e.getAmbitoTipo(), form.getAmbitoTipo())
                && java.util.Objects.equals(e.getAmbitoId(), form.getAmbitoId()))) {
            boolean existe = repo.existsByUsuario_IdAndRol_IdAndAmbitoTipoAndAmbitoId(
                    form.getUsuarioId(), form.getRolId(), form.getAmbitoTipo(), form.getAmbitoId());
            if (existe) throw new BusinessException("La asignación ya existe para ese ámbito.");
        }
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void revocar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Asignación no encontrada: " + id);
        repo.deleteById(id);
    }

    public boolean tieneAsignacion(Long usuarioId, Long rolId, com.futvia.model.common.enums.AmbitoTipo ambitoTipo, Long ambitoId){
        return repo.existsByUsuario_IdAndRol_IdAndAmbitoTipoAndAmbitoId(usuarioId, rolId, ambitoTipo, ambitoId);
    }
}
