package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import com.futvia.mapper.competicion.TemporadaMapper;
import com.futvia.model.competicion.Temporada;
import com.futvia.repository.competicion.TemporadaRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class TemporadaServiceImpl implements TemporadaService {
    private final TemporadaRepository repo;
    private final TemporadaMapper mapper;

    @Override
    public Page<TemporadaDTO> listar(Pageable p) {
        return repo.findAll(p).map(mapper::toDto);
    }

    @Override
    public Page<TemporadaDTO> porCompeticion(Long competicionId, Pageable p) {
        var list = repo.findByCompeticion_Id(competicionId)
                .stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Override @Transactional
    public TemporadaDTO crear(TemporadaFormDTO f) {
        Temporada e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public TemporadaDTO editar(Long id, TemporadaFormDTO f) {
        Temporada e = repo.findById(id).orElseThrow(() -> new NotFoundException("Temporada no encontrada"));
        mapper.update(e, f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public void eliminar(Long id) { repo.deleteById(id); }
}
