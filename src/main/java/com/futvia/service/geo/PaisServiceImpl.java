package com.futvia.service.geo;

import com.futvia.dto.geo.*; import com.futvia.mapper.geo.*;
import com.futvia.repository.geo.PaisRepository; import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*; import org.springframework.stereotype.Service; import jakarta.transaction.Transactional;

@Service @RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {
    private final PaisRepository repo; private final PaisMapper mapper;
    public Page<PaisDTO> listar(Pageable p){ return repo.findAll(p).map(mapper::toDto);}
    @Transactional public PaisDTO crear(PaisFormDTO f){ if(repo.existsByNombreIgnoreCase(f.getNombre())) throw new IllegalArgumentException("País duplicado"); return mapper.toDto(repo.save(mapper.toEntity(f))); }
}