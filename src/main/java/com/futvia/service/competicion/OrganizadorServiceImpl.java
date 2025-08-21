// src/main/java/com/futvia/service/competicion/OrganizadorServiceImpl.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.OrganizadorDTO;
import com.futvia.dto.competicion.OrganizadorFormDTO;
import com.futvia.mapper.competicion.OrganizadorMapper;
import com.futvia.model.competicion.Organizador;
import com.futvia.repository.competicion.OrganizadorRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class OrganizadorServiceImpl implements OrganizadorService {
    private final OrganizadorRepository repo;
    private final OrganizadorMapper mapper;

    public Page<OrganizadorDTO> listar(String q, String tipo, Pageable pageable){
        if (tipo != null && !tipo.isBlank()) return repo.findByTipoIgnoreCase(tipo, pageable).map(mapper::toDto);
        if (q != null && !q.isBlank()) return repo.findByNombreContainingIgnoreCase(q, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public OrganizadorDTO obtener(Long id){
        Organizador e = repo.findById(id).orElseThrow(() -> new NotFoundException("Organizador no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public OrganizadorDTO crear(OrganizadorFormDTO form){
        if (repo.existsByNombreIgnoreCase(form.getNombre()))
            throw new BusinessException("Ya existe un organizador con ese nombre.");
        return mapper.toDto(repo.save(mapper.toEntity(form)));
    }

    @Transactional
    public OrganizadorDTO editar(Long id, OrganizadorFormDTO form){
        Organizador e = repo.findById(id).orElseThrow(() -> new NotFoundException("Organizador no encontrado: " + id));
        if (!e.getNombre().equalsIgnoreCase(form.getNombre()) && repo.existsByNombreIgnoreCase(form.getNombre()))
            throw new BusinessException("Ya existe un organizador con ese nombre.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Organizador no encontrado: " + id);
        repo.deleteById(id);
    }
}
