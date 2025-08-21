// src/main/java/com/futvia/service/competicion/TemporadaServiceImpl.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.TemporadaDTO;
import com.futvia.dto.competicion.TemporadaFormDTO;
import com.futvia.mapper.competicion.TemporadaMapper;
import com.futvia.model.competicion.Temporada;
import com.futvia.repository.competicion.TemporadaRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class TemporadaServiceImpl implements TemporadaService {
    private final TemporadaRepository repo;
    private final TemporadaMapper mapper;

    public Page<TemporadaDTO> listar(Long competicionId, String q, Pageable pageable){
        if (competicionId != null && q != null && !q.isBlank())
            return repo.findByCompeticion_IdAndNombreContainingIgnoreCase(competicionId, q, pageable).map(mapper::toDto);
        if (competicionId != null) return repo.findByCompeticion_Id(competicionId, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public TemporadaDTO obtener(Long id){
        Temporada e = repo.findById(id).orElseThrow(() -> new NotFoundException("Temporada no encontrada: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public TemporadaDTO crear(TemporadaFormDTO form){
        if (repo.existsByCompeticion_IdAndNombreIgnoreCase(form.getCompeticionId(), form.getNombre()))
            throw new BusinessException("Ya existe una temporada con ese nombre en esta competición.");
        return mapper.toDto(repo.save(mapper.toEntity(form)));
    }

    @Transactional
    public TemporadaDTO editar(Long id, TemporadaFormDTO form){
        Temporada e = repo.findById(id).orElseThrow(() -> new NotFoundException("Temporada no encontrada: " + id));
        boolean cambiaClave = !e.getNombre().equalsIgnoreCase(form.getNombre()) ||
                !e.getCompeticion().getId().equals(form.getCompeticionId());
        if (cambiaClave && repo.existsByCompeticion_IdAndNombreIgnoreCase(form.getCompeticionId(), form.getNombre()))
            throw new BusinessException("Ya existe una temporada con ese nombre en esta competición.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Temporada no encontrada: " + id);
        repo.deleteById(id);
    }
}
