package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import com.futvia.mapper.geo.ZonaMapper;
import com.futvia.model.geo.Zona;
import com.futvia.repository.geo.ZonaRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ZonaServiceImpl implements ZonaService {
    private final ZonaRepository repo; private final ZonaMapper mapper;

    public Page<ZonaDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto); }

    public Page<ZonaDTO> porMunicipio(Long municipioId, Pageable p){
        var list = repo.findByMunicipio_Id(municipioId).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Transactional public ZonaDTO crear(ZonaFormDTO f){ Zona e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public ZonaDTO editar(Long id, ZonaFormDTO f){
        Zona e = repo.findById(id).orElseThrow(() -> new NotFoundException("Zona no encontrada"));
        mapper.update(e, f); return mapper.toDto(repo.save(e));
    }

    @Transactional public void eliminar(Long id){ repo.deleteById(id); }
}