package com.futvia.service.disciplina;

import com.futvia.dto.disciplina.SancionDTO;
import com.futvia.dto.disciplina.SancionFormDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.disciplina.SancionMapper;
import com.futvia.model.disciplina.Sancion;
import com.futvia.repository.disciplina.SancionRepository;
import com.futvia.service.common.NotFoundException;
import com.futvia.service.disciplina.SancionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SancionServiceImpl implements SancionService {

    private final SancionRepository repo;
    private final SancionMapper mapper;
    private final RefMapper ref;

    public SancionServiceImpl(SancionRepository repo, SancionMapper mapper, RefMapper ref) {
        this.repo = repo;
        this.mapper = mapper;
        this.ref = ref;
    }

    @Override
    @Transactional(readOnly = true)
    public SancionDTO obtener(Long id) {
        Sancion e = repo.findById(id).orElseThrow(() -> new NotFoundException("Sanción no encontrada"));
        return mapper.toDto(e, ref); // incluye jugadorId/equipoId/origenReporteId y nombres render-ready. :contentReference[oaicite:6]{index=6}
    }

    // ---------------- Listados ----------------

    @Override
    @Transactional(readOnly = true)
    public Page<SancionDTO> listarPorJugador(Long jugadorId, Pageable pageable) {
        return repo.findByJugador_Id(jugadorId, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SancionDTO> listarPorEquipo(Long equipoId, Pageable pageable) {
        return repo.findByEquipo_Id(equipoId, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SancionDTO> listarActivas(LocalDate fecha, Pageable pageable) {
        LocalDate f = fecha != null ? fecha : LocalDate.now();
        return repo.findActivas(f, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SancionDTO> listarPorRango(LocalDate desde, LocalDate hasta, Pageable pageable) {
        validarRango(desde, hasta);
        return repo.findByInterseccionRango(desde, hasta, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SancionDTO> listarPorReporte(Long reporteId, Pageable pageable) {
        return repo.findByOrigenReporte_Id(reporteId, pageable).map(e -> mapper.toDto(e, ref));
    }

    // ---------------- CRUD ----------------

    @Override
    public SancionDTO crear(SancionFormDTO form) {
        validarForm(form);
        Sancion e = mapper.toEntity(form); // mapea jugador/equipo/origenReporte por id. :contentReference[oaicite:7]{index=7}
        e = repo.save(e);
        return mapper.toDto(e, ref);
    }

    @Override
    public SancionDTO actualizar(Long id, SancionFormDTO form) {
        validarForm(form);
        Sancion actual = repo.findById(id).orElseThrow(() -> new NotFoundException("Sanción no encontrada"));
        // Aprovecha el mapper.update si lo tienes en tu archivo de mappers. :contentReference[oaicite:8]{index=8}
        mapper.update(actual, form);
        return mapper.toDto(actual, ref);
    }

    @Override
    public SancionDTO cerrar(Long id, LocalDate fin) {
        if (fin == null) throw new IllegalArgumentException("La fecha de cierre (fin) es requerida.");
        Sancion e = repo.findById(id).orElseThrow(() -> new NotFoundException("Sanción no encontrada"));
        if (e.getInicio() != null && fin.isBefore(e.getInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior al inicio de la sanción.");
        }
        e.setFin(fin);
        return mapper.toDto(e, ref);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Sanción no encontrada");
        repo.deleteById(id);
    }

    // ---------------- Atajos ----------------

    @Override
    @Transactional(readOnly = true)
    public List<SancionDTO> activasDeJugador(Long jugadorId, LocalDate fecha) {
        LocalDate f = fecha != null ? fecha : LocalDate.now();
        return repo.findActivasPorJugador(jugadorId, f).stream().map(e -> mapper.toDto(e, ref)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SancionDTO> activasDeEquipo(Long equipoId, LocalDate fecha) {
        LocalDate f = fecha != null ? fecha : LocalDate.now();
        return repo.findActivasPorEquipo(equipoId, f).stream().map(e -> mapper.toDto(e, ref)).toList();
    }

    // ---------------- Helpers ----------------

    private void validarForm(SancionFormDTO form) {
        if (form == null) throw new IllegalArgumentException("El formulario de sanción es requerido.");
        if (form.getInicio() == null) throw new IllegalArgumentException("La fecha de inicio es requerida.");
        if (form.getFin() != null && form.getFin().isBefore(form.getInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior al inicio.");
        }
        // Puedes exigir al menos jugador o equipo según tu política
        if (form.getJugadorId() == null && form.getEquipoId() == null) {
            throw new IllegalArgumentException("Debe especificarse un jugador o un equipo en la sanción.");
        }
    }

    private void validarRango(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null) throw new IllegalArgumentException("Rango de fechas inválido.");
        if (hasta.isBefore(desde)) throw new IllegalArgumentException("Rango de fechas inválido: 'hasta' < 'desde'.");
    }
}
