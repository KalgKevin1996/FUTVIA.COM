// src/main/java/com/futvia/service/club/PlantillaServiceImpl.java
package com.futvia.model.club.club;

import com.futvia.dto.club.PlantillaDTO;
import com.futvia.dto.club.PlantillaFormDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.club.PlantillaMapper;
import com.futvia.model.club.Plantilla;
import com.futvia.repository.club.PlantillaRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantillaServiceImpl implements PlantillaService {
    private final PlantillaRepository repo;
    private final PlantillaMapper mapper;
    private final RefMapper ref; // para nombreCompleto en DTO

    public Page<PlantillaDTO> listar(Long equipoId, Long temporadaId, Pageable pageable){
        if (equipoId != null && temporadaId != null) {
            List<Plantilla> list = repo.findByEquipo_IdAndTemporada_Id(equipoId, temporadaId);
            var dtos = list.stream().map(e -> mapper.toDto(e, ref)).toList();
            return new org.springframework.data.domain.PageImpl<>(dtos, pageable, dtos.size());
        }
        return repo.findAll(pageable).map(e -> mapper.toDto(e, ref));
    }

    public PlantillaDTO obtener(Long id){
        Plantilla e = repo.findById(id).orElseThrow(() -> new NotFoundException("Plantilla no encontrada: " + id));
        return mapper.toDto(e, ref);
    }

    @Transactional
    public PlantillaDTO agregar(PlantillaFormDTO form){
        // (Regla de unicidad: equipo + jugador + temporada)
        var posibles = repo.findByEquipo_IdAndTemporada_Id(form.getEquipoId(), form.getTemporadaId());
        boolean yaExiste = posibles.stream().anyMatch(p -> p.getJugador().getId().equals(form.getJugadorId()));
        if (yaExiste) throw new IllegalStateException("El jugador ya está en la plantilla de esa temporada.");
        Plantilla e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e), ref);
    }

    @Transactional
    public PlantillaDTO editar(Long id, PlantillaFormDTO form){
        Plantilla e = repo.findById(id).orElseThrow(() -> new NotFoundException("Plantilla no encontrada: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e), ref);
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Plantilla no encontrada: " + id);
        repo.deleteById(id);
    }
}
