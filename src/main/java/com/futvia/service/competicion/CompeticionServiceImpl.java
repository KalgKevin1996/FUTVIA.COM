package com.futvia.service.competicion;

import com.futvia.dto.competicion.*; import com.futvia.mapper.competicion.*;
import com.futvia.model.competicion.Competicion; import com.futvia.repository.competicion.*;
import com.futvia.service.common.NotFoundException; import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import jakarta.transaction.Transactional;

@Service @RequiredArgsConstructor
public class CompeticionServiceImpl implements CompeticionService {
    private final CompeticionRepository repo; private final CompeticionMapper mapper;
    public Page<CompeticionDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto);}
    public Page<CompeticionDTO> buscarPorNombre(String q, Pageable p){ return new PageImpl<>(repo.findByNombreContainingIgnoreCase(q).stream().map(mapper::toDto).toList(), p, 0); }
    @Transactional public CompeticionDTO crear(CompeticionFormDTO f){ return mapper.toDto(repo.save(mapper.toEntity(f))); }
    @Transactional public CompeticionDTO editar(Long id, CompeticionFormDTO f){ var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada")); mapper.update(e, f); return mapper.toDto(repo.save(e)); }
    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}