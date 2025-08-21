// src/main/java/com/futvia/service/rbac/RolServiceImpl.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.RolDTO;
import com.futvia.dto.rbac.RolFormDTO;
import com.futvia.mapper.rbac.RolMapper;
import com.futvia.model.common.enums.RolNombre;
import com.futvia.model.rbac.Rol;
import com.futvia.repository.rbac.RolRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository repo;
    private final RolMapper mapper;

    public Page<RolDTO> listar(Integer nivelMin, Integer nivelMax, Pageable pageable){
        if (nivelMin != null && nivelMax != null)
            return new PageImpl<>(repo.findByNivelBetween(nivelMin, nivelMax).stream().map(mapper::toDto).toList(), pageable, 0);
        if (nivelMin != null)
            return new PageImpl<>(repo.findByNivelGreaterThanEqual(nivelMin).stream().map(mapper::toDto).toList(), pageable, 0);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public RolDTO obtener(Long id){
        Rol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public RolDTO crear(RolFormDTO form){
        if (repo.existsByNombre(form.getNombre()))
            throw new BusinessException("El nombre de rol ya existe.");
        Rol e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public RolDTO editar(Long id, RolFormDTO form){
        Rol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado: " + id));
        if (!e.getNombre().equals(form.getNombre()) && repo.existsByNombre(form.getNombre()))
            throw new BusinessException("El nombre de rol ya existe.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Rol no encontrado: " + id);
        repo.deleteById(id);
    }
}
