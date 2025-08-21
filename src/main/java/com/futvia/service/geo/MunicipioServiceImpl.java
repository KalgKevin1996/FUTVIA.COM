// src/main/java/com/futvia/service/geo/MunicipioServiceImpl.java
package com.futvia.service.geo;

import com.futvia.dto.geo.MunicipioDTO;
import com.futvia.dto.geo.MunicipioFormDTO;
import com.futvia.mapper.geo.MunicipioMapper;
import com.futvia.model.geo.Municipio;
import com.futvia.repository.geo.MunicipioRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {
    private final MunicipioRepository repo;
    private final MunicipioMapper mapper;

    public Page<MunicipioDTO> listar(Long departamentoId, String q, Pageable pageable){
        if (departamentoId != null && q != null && !q.isBlank())
            return repo.findByDepartamento_IdAndNombreContainingIgnoreCase(departamentoId, q, pageable).map(mapper::toDto);
        if (departamentoId != null)
            return repo.findByDepartamento_Id(departamentoId, pageable).map(mapper::toDto);
        if (q != null && !q.isBlank())
            return repo.findByNombreContainingIgnoreCase(q, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public MunicipioDTO obtener(Long id){
        Municipio e = repo.findById(id).orElseThrow(() -> new NotFoundException("Municipio no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public MunicipioDTO crear(MunicipioFormDTO form){
        if (repo.existsByDepartamento_IdAndNombreIgnoreCase(form.getDepartamentoId(), form.getNombre()))
            throw new BusinessException("Ya existe un municipio con ese nombre en ese departamento.");
        Municipio e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public MunicipioDTO editar(Long id, MunicipioFormDTO form){
        Municipio e = repo.findById(id).orElseThrow(() -> new NotFoundException("Municipio no encontrado: " + id));
        boolean cambiaClave = !e.getNombre().equalsIgnoreCase(form.getNombre())
                || !e.getDepartamento().getId().equals(form.getDepartamentoId());
        if (cambiaClave && repo.existsByDepartamento_IdAndNombreIgnoreCase(form.getDepartamentoId(), form.getNombre()))
            throw new BusinessException("Ya existe un municipio con ese nombre en ese departamento.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Municipio no encontrado: " + id);
        repo.deleteById(id);
    }
}
