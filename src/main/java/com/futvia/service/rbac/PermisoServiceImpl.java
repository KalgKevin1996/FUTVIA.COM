// src/main/java/com/futvia/service/rbac/PermisoServiceImpl.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.PermisoDTO;
import com.futvia.dto.rbac.PermisoFormDTO;
import com.futvia.mapper.rbac.PermisoMapper;
import com.futvia.model.rbac.Permiso;
import com.futvia.repository.rbac.PermisoRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PermisoServiceImpl implements PermisoService {
    private final PermisoRepository repo;
    private final PermisoMapper mapper;

    public Page<PermisoDTO> listar(String q, Pageable pageable){
        if (q == null || q.isBlank()) return repo.findAll(pageable).map(mapper::toDto);
        var page = repo.findByCodigoContainingIgnoreCaseOrDescripcionContainingIgnoreCase(q, q, pageable);
        return page.map(mapper::toDto);
    }

    public PermisoDTO obtener(Long id){
        Permiso e = repo.findById(id).orElseThrow(() -> new NotFoundException("Permiso no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public PermisoDTO crear(PermisoFormDTO form){
        if (repo.existsByCodigoIgnoreCase(form.getCodigo()))
            throw new BusinessException("El código de permiso ya existe.");
        Permiso e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public PermisoDTO editar(Long id, PermisoFormDTO form){
        Permiso e = repo.findById(id).orElseThrow(() -> new NotFoundException("Permiso no encontrado: " + id));
        if (!e.getCodigo().equalsIgnoreCase(form.getCodigo()) && repo.existsByCodigoIgnoreCase(form.getCodigo()))
            throw new BusinessException("El código de permiso ya existe.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Permiso no encontrado: " + id);
        repo.deleteById(id);
    }
}
