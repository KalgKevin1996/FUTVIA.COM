package com.futvia.service.club;

import com.futvia.dto.club.*;
import com.futvia.mapper.club.JugadorMapper;
import com.futvia.model.club.Jugador;
import com.futvia.repository.club.JugadorRepository;
import com.futvia.repository.club.PlantillaRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository repo; private final JugadorMapper mapper; private final PlantillaRepository plantillaRepo;

    public Page<JugadorDTO> listar(String q, Pageable p){
        if(q == null || q.isBlank()) return repo.findAll(p).map(mapper::toDto);
        return repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(q, q, p).map(mapper::toDto);
    }

    public Page<JugadorDTO> porEquipoTemporada(Long equipoId, Long temporadaId, Pageable p){
        return plantillaRepo.findJugadoresByEquipoAndTemporada(equipoId, temporadaId, p).map(mapper::toDto);
    }

    @Transactional public JugadorDTO crear(JugadorFormDTO f){ Jugador e = mapper.toEntity(f); return mapper.toDto(repo.save(e)); }

    @Transactional public JugadorDTO editar(Long id, JugadorFormDTO f){ Jugador e = repo.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado")); mapper.update(e, f); return mapper.toDto(repo.save(e)); }

    @Transactional public void eliminar(Long id){ repo.deleteById(id);}
}