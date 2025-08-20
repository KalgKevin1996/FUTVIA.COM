package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import com.futvia.mapper.geo.DepartamentoMapper;
import com.futvia.model.geo.Departamento;
import com.futvia.repository.geo.DepartamentoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository repo; private final DepartamentoMapper mapper;

    public Page<DepartamentoDTO> listar(Pageable p){
        return repo.findAll(p).map(mapper::toDto);
    }

    public Page<DepartamentoDTO> porPais(Long paisId, Pageable p){
        var list = repo.findByPais_Id(paisId).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Transactional public DepartamentoDTO crear(DepartamentoFormDTO f){
        Departamento e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Transactional public DepartamentoDTO editar(Long id, DepartamentoFormDTO f){
        Departamento e = repo.findById(id).orElseThrow(() -> new NotFoundException("Departamento no encontrado"));
        mapper.update(e, f); return mapper.toDto(repo.save(e));
    }

    @Transactional public void eliminar(Long id){ repo.deleteById(id); }
}