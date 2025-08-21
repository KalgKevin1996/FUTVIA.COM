// src/main/java/com/futvia/service/club/CuerpoTecnicoServiceImpl.java
package com.futvia.model.club.club;

import com.futvia.dto.club.CuerpoTecnicoDTO;
import com.futvia.dto.club.CuerpoTecnicoFormDTO;
import com.futvia.mapper.club.CuerpoTecnicoMapper;
import com.futvia.repository.club.CuerpoTecnicoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuerpoTecnicoServiceImpl implements CuerpoTecnicoService {
    private final CuerpoTecnicoRepository repo;
    private final CuerpoTecnicoMapper mapper;

    public Page<CuerpoTecnicoDTO> listar(Pageable pageable){
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public java.util.List<CuerpoTecnicoDTO> listarPorEquipo(Long equipoId){
        return repo.findByEquipo_Id(equipoId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public CuerpoTecnicoDTO obtener(Long id){
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cuerpo técnico no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public CuerpoTecnicoDTO crear(CuerpoTecnicoFormDTO form){
        var e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public CuerpoTecnicoDTO editar(Long id, CuerpoTecnicoFormDTO form){
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cuerpo técnico no encontrado: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Cuerpo técnico no encontrado: " + id);
        repo.deleteById(id);
    }
}
