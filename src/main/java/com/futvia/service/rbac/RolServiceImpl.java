package com.futvia.service.rbac;


import com.futvia.dto.rbac.*;
import com.futvia.mapper.rbac.RolMapper;
import com.futvia.model.rbac.Rol;
import com.futvia.repository.rbac.RolRepository;
import com.futvia.service.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service @RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository repo; private final RolMapper mapper;
    public Page<RolDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto);}
    public RolDTO buscarPorId(Long id){ return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("Rol no encontrado")); }
    @Transactional public RolDTO crear(RolFormDTO f){ return mapper.toDto(repo.save(mapper.toEntity(f))); }
    @Transactional public RolDTO editar(Long id, RolFormDTO f){ var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado")); mapper.update(e,f); return mapper.toDto(repo.save(e)); }
    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}