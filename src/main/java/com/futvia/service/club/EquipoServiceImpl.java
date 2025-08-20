package com.futvia.service.club;

import com.futvia.dto.club.*;
import com.futvia.mapper.club.EquipoMapper;
import com.futvia.model.club.Equipo;
import com.futvia.repository.club.EquipoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository repo; private final EquipoMapper mapper;

    public Page<EquipoDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto);}

    public Page<EquipoDTO> porClub(Long clubId, Pageable p){
        var list = repo.findByClub_Id(clubId).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    public Page<EquipoDTO> porCompeticion(Long id, Pageable p){
        var list = repo.findByCompeticion_Id(id).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    public Page<EquipoDTO> porCategoria(Long id, Pageable p){
        var list = repo.findByCategoria_Id(id).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Transactional public EquipoDTO crear(EquipoFormDTO f){ Equipo e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public EquipoDTO editar(Long id, EquipoFormDTO f){ Equipo e = repo.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado")); mapper.update(e, f); return mapper.toDto(repo.save(e)); }

    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}