// src/main/java/com/futvia/service/geo/PaisServiceImpl.java
package com.futvia.service.geo;

import com.futvia.dto.geo.PaisDTO;
import com.futvia.dto.geo.PaisFormDTO;
import com.futvia.mapper.geo.PaisMapper;
import com.futvia.model.geo.Pais;
import com.futvia.repository.geo.PaisRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {
    private final PaisRepository repo;
    private final PaisMapper mapper;

    public Page<PaisDTO> listar(String q, Pageable pageable){
        if (q != null && !q.isBlank()) {
            return repo.findByNombreContainingIgnoreCase(q, pageable).map(mapper::toDto);
        }
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public PaisDTO obtener(Long id){
        Pais e = repo.findById(id).orElseThrow(() -> new NotFoundException("País no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public PaisDTO crear(PaisFormDTO form){
        if (repo.existsByNombreIgnoreCase(form.getNombre()))
            throw new BusinessException("Ya existe un país con ese nombre.");
        Pais e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public PaisDTO editar(Long id, PaisFormDTO form){
        Pais e = repo.findById(id).orElseThrow(() -> new NotFoundException("País no encontrado: " + id));
        if (!e.getNombre().equalsIgnoreCase(form.getNombre())
                && repo.existsByNombreIgnoreCase(form.getNombre()))
            throw new BusinessException("Ya existe un país con ese nombre.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("País no encontrado: " + id);
        repo.deleteById(id);
    }
}
