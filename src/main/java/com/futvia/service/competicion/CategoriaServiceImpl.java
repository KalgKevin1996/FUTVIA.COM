package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import com.futvia.mapper.competicion.CategoriaMapper;
import com.futvia.model.competicion.Categoria;
import com.futvia.repository.competicion.CategoriaRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository repo;
    private final CategoriaMapper mapper;

    @Override
    public Page<CategoriaDTO> listar(Pageable p) {
        return repo.findAll(p).map(mapper::toDto);
    }

    @Override
    public Page<CategoriaDTO> porCompeticion(Long competicionId, Pageable p) {
        var list = repo.findByCompeticion_Id(competicionId)
                .stream().map(mapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(list, p, list.size());
    }

    @Override @Transactional
    public CategoriaDTO crear(CategoriaFormDTO f) {
        Categoria e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public CategoriaDTO editar(Long id, CategoriaFormDTO f) {
        Categoria e = repo.findById(id).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        mapper.update(e, f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public void eliminar(Long id) { repo.deleteById(id); }
}
