package com.futvia.service.club;

import com.futvia.dto.club.*;
import com.futvia.mapper.club.ArbitroMapper;
import com.futvia.model.club.Arbitro;
import com.futvia.repository.club.ArbitroRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ArbitroServiceImpl implements ArbitroService {
    private final ArbitroRepository repo; private final ArbitroMapper mapper;

    public Page<ArbitroDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto);}

    public Page<ArbitroDTO> buscar(String q, Pageable p){
        if(q == null || q.isBlank()) return listar(p);
        return repo.findByNivelContainingIgnoreCaseOrAsociacionContainingIgnoreCase(q, q, p).map(mapper::toDto);
    }

    @Transactional public ArbitroDTO crear(ArbitroFormDTO f){ Arbitro e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public ArbitroDTO editar(Long id, ArbitroFormDTO f){ Arbitro e = repo.findById(id).orElseThrow(() -> new NotFoundException("Árbitro no encontrado")); mapper.update(e, f); return mapper.toDto(repo.save(e)); }

    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}