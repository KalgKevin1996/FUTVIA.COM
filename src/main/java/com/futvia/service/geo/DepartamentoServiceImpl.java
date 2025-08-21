// src/main/java/com/futvia/service/geo/DepartamentoServiceImpl.java
package com.futvia.service.geo;

import com.futvia.dto.geo.DepartamentoDTO;
import com.futvia.dto.geo.DepartamentoFormDTO;
import com.futvia.mapper.geo.DepartamentoMapper;
import com.futvia.model.geo.Departamento;
import com.futvia.repository.geo.DepartamentoRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository repo;
    private final DepartamentoMapper mapper;

    public Page<DepartamentoDTO> listar(Long paisId, String q, Pageable pageable){
        if (paisId != null && q != null && !q.isBlank())
            return repo.findByPais_IdAndNombreContainingIgnoreCase(paisId, q, pageable).map(mapper::toDto);
        if (paisId != null)
            return repo.findByPais_Id(paisId, pageable).map(mapper::toDto);
        if (q != null && !q.isBlank())
            return repo.findByNombreContainingIgnoreCase(q, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public DepartamentoDTO obtener(Long id){
        Departamento e = repo.findById(id).orElseThrow(() -> new NotFoundException("Departamento no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public DepartamentoDTO crear(DepartamentoFormDTO form){
        if (repo.existsByPais_IdAndNombreIgnoreCase(form.getPaisId(), form.getNombre()))
            throw new BusinessException("Ya existe un departamento con ese nombre en ese país.");
        Departamento e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public DepartamentoDTO editar(Long id, DepartamentoFormDTO form){
        Departamento e = repo.findById(id).orElseThrow(() -> new NotFoundException("Departamento no encontrado: " + id));
        boolean cambiaClave = !e.getNombre().equalsIgnoreCase(form.getNombre())
                || !e.getPais().getId().equals(form.getPaisId());
        if (cambiaClave && repo.existsByPais_IdAndNombreIgnoreCase(form.getPaisId(), form.getNombre()))
            throw new BusinessException("Ya existe un departamento con ese nombre en ese país.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Departamento no encontrado: " + id);
        repo.deleteById(id);
    }
}
