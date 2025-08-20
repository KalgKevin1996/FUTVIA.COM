package com.futvia.service.competicion;

import com.futvia.dto.competicion.*;
import com.futvia.mapper.competicion.OrganizadorMapper;
import com.futvia.model.competicion.Organizador;
import com.futvia.repository.competicion.OrganizadorRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class OrganizadorServiceImpl implements OrganizadorService {
    private final OrganizadorRepository repo;
    private final OrganizadorMapper mapper;

    @Override public Page<OrganizadorDTO> listar(Pageable p) {
        return repo.findAll(p).map(mapper::toDto);
    }

    @Override @Transactional
    public OrganizadorDTO crear(OrganizadorFormDTO f) {
        Organizador e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public OrganizadorDTO editar(Long id, OrganizadorFormDTO f) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Organizador no encontrado"));
        mapper.update(e, f);
        return mapper.toDto(repo.save(e));
    }

    @Override @Transactional
    public void eliminar(Long id) { repo.deleteById(id); }
}
