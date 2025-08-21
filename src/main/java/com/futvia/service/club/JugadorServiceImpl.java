// src/main/java/com/futvia/service/club/JugadorServiceImpl.java
package com.futvia.service.club;

import com.futvia.dto.club.JugadorDTO;
import com.futvia.dto.club.JugadorFormDTO;
import com.futvia.mapper.club.JugadorMapper;
import com.futvia.model.club.Jugador;
import com.futvia.repository.club.JugadorRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository repo;
    private final JugadorMapper mapper;

    public Page<JugadorDTO> buscar(String q, Pageable pageable){
        if (q == null || q.isBlank()) {
            return repo.findAll(pageable).map(mapper::toDto);
        }
        var lista = repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(q, q);
        return new org.springframework.data.domain.PageImpl<>(
                lista.stream().map(mapper::toDto).toList(), pageable, lista.size());
    }

    public JugadorDTO obtener(Long id){
        Jugador e = repo.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Transactional
    public JugadorDTO crear(JugadorFormDTO form){
        Jugador e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public JugadorDTO editar(Long id, JugadorFormDTO form){
        Jugador e = repo.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado: " + id));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id){
        if (!repo.existsById(id)) throw new NotFoundException("Jugador no encontrado: " + id);
        repo.deleteById(id);
    }
}
