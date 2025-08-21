package com.futvia.service.partido;


import com.futvia.dto.partido.*;
import com.futvia.mapper.partido.*;
import com.futvia.model.common.enums.EstadoPartido;
import com.futvia.model.common.enums.TipoIncidencia;
import com.futvia.model.partido.*;
import com.futvia.repository.partido.*;
import com.futvia.repository.evento.IncidenciaPartidoRepository;
import com.futvia.service.partido.*;
import com.futvia.service.common.NotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepo;
    private final PartidoMapper partidoMapper;
    private final IncidenciaPartidoRepository incidenciaRepo;

    PartidoServiceImpl(
            PartidoRepository partidoRepo,
            PartidoMapper partidoMapper,
            IncidenciaPartidoRepository incidenciaRepo
    ) {
        this.partidoRepo = partidoRepo;
        this.partidoMapper = partidoMapper;
        this.incidenciaRepo = incidenciaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartidoDTO> listar(Long temporadaId, EstadoPartido estado, Pageable pageable) {
        if (temporadaId != null && estado != null) {
            // método derivado existente + paginación manual
            List<Partido> list = partidoRepo.findByTemporada_IdAndEstadoOrderByFechaHoraAsc(temporadaId, estado);
            return toPage(list, pageable).map(partidoMapper::toDto);
        }
        return partidoRepo.findAll(pageable).map(partidoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartidoDTO> proximos(EstadoPartido estado, LocalDateTime desde, Pageable pageable) {
        List<Partido> list = partidoRepo.findTop20ByEstadoAndFechaHoraAfterOrderByFechaHoraAsc(
                estado != null ? estado : EstadoPartido.PROGRAMADO,
                desde != null ? desde : LocalDateTime.now()
        );
        return toPage(list, pageable).map(partidoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PartidoDTO obtener(Long id) {
        Partido e = partidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        return partidoMapper.toDto(e);
    }

    @Override
    public PartidoDTO crear(PartidoFormDTO form) {
        validarEquiposDistintos(form.getEquipoLocalId(), form.getEquipoVisitanteId());
        validarColisionesHorario(form.getFechaHora(), form.getEquipoLocalId(), form.getEquipoVisitanteId(), null);

        Partido e = partidoMapper.toEntity(form);
        e.setEstado(e.getEstado() == null ? EstadoPartido.PROGRAMADO : e.getEstado());
        e = partidoRepo.save(e);
        return partidoMapper.toDto(e);
    }

    @Override
    public PartidoDTO reprogramar(Long id, LocalDateTime nuevaFechaHora, Long nuevoEstadioId) {
        Partido e = partidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        validarColisionesHorario(nuevaFechaHora, e.getEquipoLocal().getId(), e.getEquipoVisitante().getId(), e.getId());
        e.setFechaHora(nuevaFechaHora);
        if (nuevoEstadioId != null) {
            Estadio estadio = new Estadio(); estadio.setId(nuevoEstadioId);
            e.setEstadio(estadio);
        }
        return partidoMapper.toDto(e);
    }

    @Override
    public PartidoDTO cambiarEstado(Long id, EstadoPartido nuevoEstado) {
        Partido e = partidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        e.setEstado(nuevoEstado);
        // si lo pasas a FINALIZADO sin marcador, puedes llamar a cerrarYFijarMarcador desde el controlador
        return partidoMapper.toDto(e);
    }

    @Override
    public PartidoDTO cerrarYFijarMarcador(Long id) {
        Partido e = partidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));

        Long partidoId = e.getId();
        Long localId = e.getEquipoLocal().getId();
        Long visId = e.getEquipoVisitante().getId();

        // Goles del local = GOL del local + AUTOGOL del visitante
        long golesLocal = incidenciaRepo.countByTipoAndPartido_IdAndEquipo_Id(TipoIncidencia.GOL, partidoId, localId)
                + incidenciaRepo.countAutogolesRival(partidoId, localId);

        // Goles del visitante = GOL del visitante + AUTOGOL del local
        long golesVisitante = incidenciaRepo.countByTipoAndPartido_IdAndEquipo_Id(TipoIncidencia.GOL, partidoId, visId)
                + incidenciaRepo.countAutogolesRival(partidoId, visId);

        e.setGolesLocal((int) golesLocal);
        e.setGolesVisitante((int) golesVisitante);
        e.setEstado(EstadoPartido.FINALIZADO);

        return partidoMapper.toDto(e);
    }

    @Override
    public void eliminar(Long id) {
        if (!partidoRepo.existsById(id)) throw new NotFoundException("Partido no encontrado");
        partidoRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartidoDTO> fixtureEquipo(Long temporadaId, Long equipoId, Pageable pageable) {
        // ejemplo simple: filtra en memoria desde temporada (puedes crear query dedicada si gustas)
        List<Partido> base = temporadaId != null
                ? partidoRepo.findByTemporada_IdAndEstadoOrderByFechaHoraAsc(temporadaId, EstadoPartido.PROGRAMADO)
                : partidoRepo.findAll();

        List<Partido> filtrado = base.stream()
                .filter(p -> Objects.equals(p.getEquipoLocal().getId(), equipoId) || Objects.equals(p.getEquipoVisitante().getId(), equipoId))
                .collect(Collectors.toList());

        return toPage(filtrado, pageable).map(partidoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartidoDTO> headToHead(Long temporadaId, Long equipoAId, Long equipoBId, int maxResultados) {
        List<Partido> base = temporadaId != null
                ? partidoRepo.findByTemporada_IdAndEstadoOrderByFechaHoraAsc(temporadaId, EstadoPartido.FINALIZADO)
                : partidoRepo.findAll();

        return base.stream()
                .filter(p ->
                        (Objects.equals(p.getEquipoLocal().getId(), equipoAId) && Objects.equals(p.getEquipoVisitante().getId(), equipoBId)) ||
                                (Objects.equals(p.getEquipoLocal().getId(), equipoBId) && Objects.equals(p.getEquipoVisitante().getId(), equipoAId))
                )
                .sorted(Comparator.comparing(Partido::getFechaHora).reversed())
                .limit(maxResultados > 0 ? maxResultados : 10)
                .map(partidoMapper::toDto)
                .toList();
    }

    // ---- helpers ----

    private void validarEquiposDistintos(Long localId, Long visId) {
        if (Objects.equals(localId, visId)) {
            throw new IllegalArgumentException("El equipo local y el visitante deben ser distintos");
        }
    }

    private void validarColisionesHorario(LocalDateTime fecha, Long localId, Long visId, Long excluirPartidoId) {
        // Heurística sencilla: ventana +/- 2 horas para detectar choques por equipo
        LocalDateTime desde = fecha.minusHours(2);
        LocalDateTime hasta = fecha.plusHours(2);

        // Puedes crear métodos dedicados en el repository para optimizar. Aquí, ejemplo en memoria:
        List<Partido> delDia = partidoRepo.findAll().stream()
                .filter(p -> excluirPartidoId == null || !p.getId().equals(excluirPartidoId))
                .filter(p -> !p.getEstado().equals(EstadoPartido.SUSPENDIDO))
                .filter(p -> !p.getFechaHora().isBefore(desde) && !p.getFechaHora().isAfter(hasta))
                .toList();

        boolean choque = delDia.stream().anyMatch(p ->
                Objects.equals(p.getEquipoLocal().getId(), localId) ||
                        Objects.equals(p.getEquipoVisitante().getId(), localId) ||
                        Objects.equals(p.getEquipoLocal().getId(), visId) ||
                        Objects.equals(p.getEquipoVisitante().getId(), visId)
        );
        if (choque) throw new IllegalStateException("Colisión de horario para uno de los equipos en la ventana +/- 2h");
    }

    private <T> Page<T> toPage(List<T> list, Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int from = Math.min(page * size, list.size());
        int to = Math.min(from + size, list.size());
        List<T> slice = list.subList(from, to);
        return new PageImpl<>(slice, pageable, list.size());
    }
}