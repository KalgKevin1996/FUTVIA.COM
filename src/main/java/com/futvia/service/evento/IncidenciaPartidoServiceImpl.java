package com.futvia.service.evento;

import com.futvia.dto.evento.IncidenciaPartidoDTO;
import com.futvia.dto.evento.IncidenciaPartidoFormDTO;
import com.futvia.mapper.RefMapper; // <-- NUEVO
import com.futvia.mapper.evento.IncidenciaPartidoMapper;
import com.futvia.model.common.enums.TipoIncidencia;
import com.futvia.model.evento.IncidenciaPartido;
import com.futvia.repository.evento.IncidenciaPartidoRepository;
import com.futvia.repository.partido.PartidoRepository;
import com.futvia.repository.club.EquipoRepository;
import com.futvia.repository.club.JugadorRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidenciaPartidoServiceImpl implements IncidenciaPartidoService {

    private final IncidenciaPartidoRepository repo;
    private final PartidoRepository partidoRepo;
    private final EquipoRepository equipoRepo;
    private final JugadorRepository jugadorRepo;
    private final IncidenciaPartidoMapper mapper;
    private final RefMapper ref; // <-- NUEVO

    @Override
    public Page<IncidenciaPartidoDTO> listarPorPartido(Long partidoId, Pageable pageable) {
        return repo.findByPartido_Id(partidoId, pageable)
                .map(e -> mapper.toDto(e, ref)); // <-- usa @Context RefMapper
    }

    @Override
    public Page<IncidenciaPartidoDTO> listarPorPartidoYEquipo(Long partidoId, Long equipoId, Pageable pageable) {
        // Opción A (recomendada): método paginado en el repo (ver punto 2)
        return repo.findByPartido_IdAndEquipo_Id(partidoId, equipoId, pageable)
                .map(e -> mapper.toDto(e, ref));

        // Opción B (temporal si no agregas el método paginado):
        // var list = repo.findByPartido_IdAndEquipo_Id(partidoId, equipoId);
        // var content = list.stream().map(e -> mapper.toDto(e, ref)).toList();
        // return new PageImpl<>(content, pageable, list.size());
    }

    @Transactional
    @Override
    public IncidenciaPartidoDTO crear(IncidenciaPartidoFormDTO form) {
        IncidenciaPartido entidad = mapper.toEntity(form);
        var partido = partidoRepo.findById(form.getPartidoId())
                .orElseThrow(() -> new NotFoundException("Partido no existe"));
        entidad.setPartido(partido);

        if (form.getEquipoId() != null) {
            var equipo = equipoRepo.findById(form.getEquipoId())
                    .orElseThrow(() -> new NotFoundException("Equipo no existe"));
            entidad.setEquipo(equipo);
        }
        if (form.getJugadorPrincipalId() != null) {
            var jug = jugadorRepo.findById(form.getJugadorPrincipalId())
                    .orElseThrow(() -> new NotFoundException("Jugador principal no existe"));
            entidad.setJugadorPrincipal(jug);
        }
        if (form.getJugadorInvolucradoId() != null) {
            var inv = jugadorRepo.findById(form.getJugadorInvolucradoId())
                    .orElseThrow(() -> new NotFoundException("Jugador involucrado no existe"));
            entidad.setJugadorInvolucrado(inv);
        }
        return mapper.toDto(repo.save(entidad), ref); // <-- usa ref
    }

    @Transactional
    @Override
    public IncidenciaPartidoDTO editar(Long id, IncidenciaPartidoFormDTO form) {
        IncidenciaPartido e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no existe"));

        e.setMinuto(form.getMinuto());
        e.setTiempo(form.getTiempo());
        e.setTipo(form.getTipo());

        if (form.getEquipoId() != null) {
            e.setEquipo(equipoRepo.findById(form.getEquipoId())
                    .orElseThrow(() -> new NotFoundException("Equipo no existe")));
        } else {
            e.setEquipo(null);
        }

        if (form.getJugadorPrincipalId() != null) {
            e.setJugadorPrincipal(jugadorRepo.findById(form.getJugadorPrincipalId())
                    .orElseThrow(() -> new NotFoundException("Jugador principal no existe")));
        } else {
            e.setJugadorPrincipal(null);
        }

        if (form.getJugadorInvolucradoId() != null) {
            e.setJugadorInvolucrado(jugadorRepo.findById(form.getJugadorInvolucradoId())
                    .orElseThrow(() -> new NotFoundException("Jugador involucrado no existe")));
        } else {
            e.setJugadorInvolucrado(null);
        }

        e.setDetalle(form.getDetalle());
        return mapper.toDto(repo.save(e), ref); // <-- usa ref
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        IncidenciaPartido e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no existe"));
        repo.delete(e);
    }

    @Override
    public long golesAFavor(Long partidoId, Long equipoId) {
        return repo.countByTipoAndPartido_IdAndEquipo_Id(TipoIncidencia.GOL, partidoId, equipoId);
    }

    @Override
    public long autogolesDelRival(Long partidoId, Long equipoId) {
        return repo.countAutogolesRival(partidoId, equipoId);
    }

    @Override
    public long golesDeJugadorEnTemporada(Long jugadorId, Long temporadaId) {
        return repo.countGolesJugadorEnTemporada(jugadorId, temporadaId);
    }
}
