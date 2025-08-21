// src/main/java/com/futvia/service/club/EquipoServiceImpl.java
package com.futvia.service.club;

import com.futvia.dto.club.EquipoDTO;
import com.futvia.dto.club.EquipoFormDTO;
import com.futvia.mapper.club.EquipoMapper;
import com.futvia.model.club.Equipo;
import com.futvia.repository.club.EquipoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository repo;
    private final EquipoMapper mapper;

    public Page<EquipoDTO> listar(Long competicionId, Long categoriaId, Pageable pageable){
        List<Equipo> datos;
        if (competicionId != null && categoriaId != null) {
            datos = repo.findByCompeticion_Id(competicionId).stream()
                    .filter(e -> e.getCategoria()!=null && e.getCategoria().getId().equals(categoriaId))
                    .toList();
        } else if (competicionId != null) {
            datos = repo.findByCompeticion_Id(competicionId);
        } else if (categoriaId != null) {
            datos = repo.findByCategoria_Id(categoriaId);
        } else {
            return repo.findAll(pageable).map(mapper::toDto);
        }
        var page = new org.springframework.data.domain.PageImpl<>(
                datos.stream().map(mapper::toDto).toList(), pageable, datos.size());
        return page;
    }

    public EquipoDTO obtener(Long id){
        Equipo e = repo.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public EquipoDTO crear(EquipoFormDTO form){
        Equipo e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public EquipoDTO editar(Long id, EquipoFormDTO form){
        Equipo e = repo.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Equipo no encontrado: " + id);
        repo.deleteById(id);
    }
}
