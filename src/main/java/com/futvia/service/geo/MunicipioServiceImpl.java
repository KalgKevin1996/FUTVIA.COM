package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import com.futvia.mapper.geo.MunicipioMapper;
import com.futvia.model.geo.Municipio;
import com.futvia.repository.geo.MunicipioRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {
    private final MunicipioRepository repo; private final MunicipioMapper mapper;

    public Page<MunicipioDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto); }

    public Page<MunicipioDTO> porDepartamento(Long departamentoId, Pageable p){
        var list = repo.findByDepartamento_Id(departamentoId).stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Transactional public MunicipioDTO crear(MunicipioFormDTO f){ Municipio e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public MunicipioDTO editar(Long id, MunicipioFormDTO f){
        Municipio e = repo.findById(id).orElseThrow(() -> new NotFoundException("Municipio no encontrado"));
        mapper.update(e, f); return mapper.toDto(repo.save(e));
    }

    @Transactional public void eliminar(Long id){ repo.deleteById(id); }
}