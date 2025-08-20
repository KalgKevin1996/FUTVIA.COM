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

    public Page<ClubDTO> listar(Pageable p){
        return repo.findAll(p).map(mapper::toDto);
    }

    public Page<ClubDTO> buscar(String q, Pageable p){
        if (q == null || q.isBlank()) return listar(p);
        return repo.findByNombreContainingIgnoreCase(q, p).map(mapper::toDto);
    }

    @Transactional
    public ClubDTO crear(ClubFormDTO f){
        Club e = mapper.toEntity(f);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public ClubDTO editar(Long id, ClubFormDTO f){
        Club e = repo.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
        mapper.update(e, f);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) {
            throw new NotFoundException("Club no encontrado");
        }
        repo.deleteById(id);
    }
}
