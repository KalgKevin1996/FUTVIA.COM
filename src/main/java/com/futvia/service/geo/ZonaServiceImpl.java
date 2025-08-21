// src/main/java/com/futvia/service/geo/ZonaServiceImpl.java
package com.futvia.service.geo;

import com.futvia.dto.geo.ZonaDTO;
import com.futvia.dto.geo.ZonaFormDTO;
import com.futvia.mapper.geo.ZonaMapper;
import com.futvia.model.geo.Zona;
import com.futvia.repository.geo.ZonaRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ZonaServiceImpl implements ZonaService {
    private final ZonaRepository repo;
    private final ZonaMapper mapper;

    public Page<ZonaDTO> listar(Long municipioId, Integer numero, Pageable pageable){
        if (municipioId != null && numero != null)
            return repo.findByMunicipio_IdAndNumero(municipioId, numero, pageable).map(mapper::toDto);
        if (municipioId != null)
            return repo.findByMunicipio_Id(municipioId, pageable).map(mapper::toDto);
        if (numero != null)
            return repo.findByNumero(numero, pageable).map(mapper::toDto);
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public ZonaDTO obtener(Long id){
        Zona e = repo.findById(id).orElseThrow(() -> new NotFoundException("Zona no encontrada: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public ZonaDTO crear(ZonaFormDTO form){
        if (repo.existsByMunicipio_IdAndNumero(form.getMunicipioId(), form.getNumero()))
            throw new BusinessException("Ya existe esa zona en el municipio.");
        Zona e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public ZonaDTO editar(Long id, ZonaFormDTO form){
        Zona e = repo.findById(id).orElseThrow(() -> new NotFoundException("Zona no encontrada: " + id));
        boolean cambiaClave = !e.getMunicipio().getId().equals(form.getMunicipioId())
                || !e.getNumero().equals(form.getNumero());
        if (cambiaClave && repo.existsByMunicipio_IdAndNumero(form.getMunicipioId(), form.getNumero()))
            throw new BusinessException("Ya existe esa zona en el municipio.");
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Zona no encontrada: " + id);
        repo.deleteById(id);
    }
}
