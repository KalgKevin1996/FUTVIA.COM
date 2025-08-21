// src/main/java/com/futvia/service/competicion/CategoriaServiceImpl.java
package com.futvia.service.competicion;

import com.futvia.dto.competicion.CategoriaDTO;
import com.futvia.dto.competicion.CategoriaFormDTO;
import com.futvia.mapper.competicion.CategoriaMapper;
import com.futvia.model.competicion.Categoria;
import com.futvia.repository.competicion.CategoriaRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repo;
    private final CategoriaMapper mapper;

    public Page<CategoriaDTO> listar(Long competicionId, String q, Pageable pageable){
        if (competicionId != null && q != null && !q.isBlank())
            return repo.findByCompeticion_IdAndNombreContainingIgnoreCase(competicionId, q, pageable).map(mapper::toDto);
        if (competicionId != null) return repo.findByCompeticion_Id(competicionId, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public CategoriaDTO obtener(Long id){
        Categoria e = repo.findById(id).orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public CategoriaDTO crear(CategoriaFormDTO form){
        if (repo.existsByCompeticion_IdAndNombreIgnoreCase(form.getCompeticionId(), form.getNombre()))
            throw new BusinessException("Ya existe una categoría con ese nombre en esta competición.");
        return mapper.toDto(repo.save(mapper.toEntity(form)));
    }

    @Transactional
    public CategoriaDTO editar(Long id, CategoriaFormDTO form){
        Categoria e = repo.findById(id).orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
        boolean cambiaClave = !e.getNombre().equalsIgnoreCase(form.getNombre()) ||
                !e.getCompeticion().getId().equals(form.getCompeticionId());
        if (cambiaClave && repo.existsByCompeticion_IdAndNombreIgnoreCase(form.getCompeticionId(), form.getNombre()))
            throw new BusinessException("Ya existe una categoría con ese nombre en esta competición.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Categoría no encontrada: " + id);
        repo.deleteById(id);
    }
}
