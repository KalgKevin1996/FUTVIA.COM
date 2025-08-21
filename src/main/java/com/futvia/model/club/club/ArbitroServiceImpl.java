// src/main/java/com/futvia/service/club/ArbitroServiceImpl.java
package com.futvia.model.club.club;

import com.futvia.dto.club.ArbitroDTO;
import com.futvia.dto.club.ArbitroFormDTO;
import com.futvia.mapper.club.ArbitroMapper;
import com.futvia.model.club.Arbitro;
import com.futvia.repository.club.ArbitroRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArbitroServiceImpl implements ArbitroService {
    private final ArbitroRepository repo;
    private final ArbitroMapper mapper;

    public Page<ArbitroDTO> listar(Pageable pageable){
        return repo.findAll(pageable).map(mapper::toDto);
    }

    public ArbitroDTO obtener(Long id){
        Arbitro e = repo.findById(id).orElseThrow(() -> new NotFoundException("Árbitro no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public ArbitroDTO crear(ArbitroFormDTO form){
        Arbitro e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public ArbitroDTO editar(Long id, ArbitroFormDTO form){
        Arbitro e = repo.findById(id).orElseThrow(() -> new NotFoundException("Árbitro no encontrado: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Árbitro no encontrado: " + id);
        repo.deleteById(id);
    }
}
