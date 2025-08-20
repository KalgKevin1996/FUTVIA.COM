package com.futvia.service.club;

import com.futvia.dto.club.*;
import com.futvia.mapper.club.PlantillaMapper;
import com.futvia.model.club.Plantilla;
import com.futvia.repository.club.PlantillaRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class PlantillaServiceImpl implements PlantillaService {
    private final PlantillaRepository repo; private final PlantillaMapper mapper;

    public Page<PlantillaDTO> porEquipo(Long equipoId, Long temporadaId, Pageable p){
        return repo.findByEquipo_IdAndTemporada_Id(equipoId, temporadaId, p).map(mapper::toDto);
    }

    @Transactional public PlantillaDTO agregar(PlantillaFormDTO f){
        boolean existe = repo.existsByEquipo_IdAndJugador_IdAndTemporada_Id(f.getEquipoId(), f.getJugadorId(), f.getTemporadaId());
        if(existe) throw new BusinessException("El jugador ya está en la plantilla para esa temporada");
        Plantilla e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Transactional public PlantillaDTO editar(Long id, PlantillaFormDTO f){
        Plantilla e = repo.findById(id).orElseThrow(() -> new NotFoundException("Plantilla no encontrada"));
        mapper.update(e, f); return mapper.toDto(repo.save(e));
    }

    @Transactional public void activar(Long id, boolean activo){
        Plantilla e = repo.findById(id).orElseThrow(() -> new NotFoundException("Plantilla no encontrada"));
        e.setActivo(activo); repo.save(e);
    }

    @Transactional public void eliminar(Long id){ repo.deleteById(id); }
}