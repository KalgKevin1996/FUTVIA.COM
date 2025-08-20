package com.futvia.service.club;

import com.futvia.dto.club.*;
import com.futvia.mapper.club.CuerpoTecnicoMapper;
import com.futvia.model.club.CuerpoTecnico;
import com.futvia.repository.club.CuerpoTecnicoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class CuerpoTecnicoServiceImpl implements CuerpoTecnicoService {
    private final CuerpoTecnicoRepository repo; private final CuerpoTecnicoMapper mapper;

    public Page<CuerpoTecnicoDTO> porEquipo(Long equipoId, Pageable p){
        return repo.findByEquipo_Id(equipoId, p).map(mapper::toDto);
    }

    @Transactional public CuerpoTecnicoDTO crear(CuerpoTecnicoFormDTO f){ CuerpoTecnico e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public CuerpoTecnicoDTO editar(Long id, CuerpoTecnicoFormDTO f){ CuerpoTecnico e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cuerpo técnico no encontrado")); mapper.update(e, f); return mapper.toDto(repo.save(e)); }

    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}