// src/main/java/com/futvia/service/club/ClubServiceImpl.java
package com.futvia.service.club;

import com.futvia.dto.club.ClubDTO;
import com.futvia.dto.club.ClubFormDTO;
import com.futvia.mapper.club.ClubMapper;
import com.futvia.model.club.Club;
import com.futvia.repository.club.ClubRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubRepository repo;
    private final ClubMapper mapper;

    public Page<ClubDTO> listar(String q, Pageable pageable){
        if (q == null || q.isBlank()) {
            return repo.findAll(pageable).map(mapper::toDto);
        }
        // Si tienes el paginado findByNombre... usa ese, si no, mapea la lista manual:
        var lista = repo.findByNombreContainingIgnoreCase(q);
        return new org.springframework.data.domain.PageImpl<>(
                lista.stream().map(mapper::toDto).toList(), pageable, lista.size());
    }

    public ClubDTO obtener(Long id){
        Club e = repo.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public ClubDTO crear(ClubFormDTO form){
        Club e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public ClubDTO editar(Long id, ClubFormDTO form){
        Club e = repo.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Club no encontrado: " + id);
        repo.deleteById(id);
    }
}
