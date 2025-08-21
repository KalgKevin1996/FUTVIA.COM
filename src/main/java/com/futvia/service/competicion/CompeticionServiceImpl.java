// src/main/java/com/futvia/service/competicion/CompeticionServiceImpl.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.CompeticionDTO;
import com.futvia.dto.competicion.CompeticionFormDTO;
import com.futvia.mapper.competicion.CompeticionMapper;
import com.futvia.model.competicion.Competicion;
import com.futvia.model.common.enums.TipoCompeticion;
import com.futvia.repository.competicion.CompeticionRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CompeticionServiceImpl implements CompeticionService {
    private final CompeticionRepository repo;
    private final CompeticionMapper mapper;

    public Page<CompeticionDTO> listar(Long organizadorId, TipoCompeticion tipo, String q, Pageable pageable){
        if (organizadorId != null) return repo.findByOrganizador_Id(organizadorId, pageable).map(mapper::toDto);
        if (tipo != null) return repo.findByTipo(tipo, pageable).map(mapper::toDto);
        if (q != null && !q.isBlank()) return repo.findByNombreContainingIgnoreCase(q, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public CompeticionDTO obtener(Long id){
        Competicion e = repo.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public CompeticionDTO crear(CompeticionFormDTO form){
        if (repo.existsByOrganizador_IdAndNombreIgnoreCase(form.getOrganizadorId(), form.getNombre()))
            throw new BusinessException("Ya existe una competición con ese nombre en ese organizador.");
        return mapper.toDto(repo.save(mapper.toEntity(form)));
    }

    @Transactional
    public CompeticionDTO editar(Long id, CompeticionFormDTO form){
        Competicion e = repo.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada: " + id));
        boolean cambiaClave = !e.getNombre().equalsIgnoreCase(form.getNombre()) ||
                !e.getOrganizador().getId().equals(form.getOrganizadorId());
        if (cambiaClave && repo.existsByOrganizador_IdAndNombreIgnoreCase(form.getOrganizadorId(), form.getNombre()))
            throw new BusinessException("Ya existe una competición con ese nombre en ese organizador.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Competición no encontrada: " + id);
        repo.deleteById(id);
    }
}
