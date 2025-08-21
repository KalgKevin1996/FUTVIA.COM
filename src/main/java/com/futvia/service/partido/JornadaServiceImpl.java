// src/main/java/com/futvia/service/partido/impl/JornadaServiceImpl.java
package com.futvia.service.partido;

import com.futvia.dto.partido.JornadaDTO;
import com.futvia.dto.partido.JornadaFormDTO;
import com.futvia.mapper.partido.JornadaMapper;
import com.futvia.model.partido.Jornada;
import com.futvia.repository.partido.JornadaRepository;
import com.futvia.service.common.NotFoundException;
import com.futvia.service.partido.JornadaService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class JornadaServiceImpl implements JornadaService {

    private final JornadaRepository jornadaRepo;
    private final JornadaMapper jornadaMapper;

    public JornadaServiceImpl(JornadaRepository jornadaRepo, JornadaMapper jornadaMapper) {
        this.jornadaRepo = jornadaRepo;
        this.jornadaMapper = jornadaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JornadaDTO> listarPorTemporada(Long temporadaId, Pageable pageable) {
        if (temporadaId == null) {
            // Si no se da temporada, lista todas ordenadas por id (fallback)
            return jornadaRepo.findAll(pageable).map(jornadaMapper::toDto);
        }
        // Preferimos el método con orden por número asc
        Page<Jornada> page = jornadaRepo.findByTemporada_IdOrderByNumeroAsc(temporadaId, pageable);
        return page.map(jornadaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public JornadaDTO obtener(Long id) {
        Jornada e = jornadaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Jornada no encontrada"));
        return jornadaMapper.toDto(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JornadaDTO> buscarPorNumero(Long temporadaId, Integer numero) {
        if (temporadaId == null || numero == null) return Optional.empty();
        return jornadaRepo.findByTemporada_IdAndNumero(temporadaId, numero)
                .map(jornadaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JornadaDTO> listarRangoFechas(Long temporadaId, LocalDate desde, LocalDate hasta, Pageable pageable) {
        if (temporadaId == null || desde == null || hasta == null) {
            return Page.empty(pageable);
        }
        validarRango(desde, hasta);

        List<Jornada> lista = jornadaRepo.findByTemporada_IdAndFechaInicioBetween(temporadaId, desde, hasta);
        // Orden consistente por número asc
        lista.sort(Comparator.comparing(Jornada::getNumero, Comparator.nullsLast(Integer::compareTo)));
        return toPage(lista.stream().map(jornadaMapper::toDto).toList(), pageable);
    }

    @Override
    public JornadaDTO crear(JornadaFormDTO form) {
        validarForm(form);
        // Unicidad: (temporada, numero)
        if (form.getTemporadaId() != null && form.getNumero() != null) {
            boolean existe = jornadaRepo.findByTemporada_IdAndNumero(form.getTemporadaId(), form.getNumero()).isPresent();
            if (existe) {
                throw new IllegalStateException("Ya existe una jornada con ese número para la temporada indicada.");
            }
        }
        Jornada e = jornadaMapper.toEntity(form);
        e = jornadaRepo.save(e);
        return jornadaMapper.toDto(e);
    }

    @Override
    public JornadaDTO actualizar(Long id, JornadaFormDTO form) {
        validarForm(form);
        Jornada actual = jornadaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Jornada no encontrada"));

        // Si cambia (temporada, numero), validar unicidad
        boolean cambiaTemporada = !equalsId(actual.getTemporada(), form.getTemporadaId());
        boolean cambiaNumero = !Objects.equals(actual.getNumero(), form.getNumero());

        if ((cambiaTemporada || cambiaNumero) && form.getTemporadaId() != null && form.getNumero() != null) {
            var choque = jornadaRepo.findByTemporada_IdAndNumero(form.getTemporadaId(), form.getNumero());
            if (choque.isPresent() && !choque.get().getId().equals(id)) {
                throw new IllegalStateException("Ya existe otra jornada con ese número en la misma temporada.");
            }
        }

        // Aplicar cambios
        jornadaMapper.update(actual, form);
        return jornadaMapper.toDto(actual);
    }

    @Override
    public void eliminar(Long id) {
        if (!jornadaRepo.existsById(id)) {
            throw new NotFoundException("Jornada no encontrada");
        }
        jornadaRepo.deleteById(id);
    }

    // --------- Helpers ---------

    private void validarForm(JornadaFormDTO form) {
        if (form == null) throw new IllegalArgumentException("El formulario de jornada es requerido.");
        if (form.getFechaInicio() != null && form.getFechaFin() != null) {
            validarRango(form.getFechaInicio(), form.getFechaFin());
        }
        if (form.getNumero() == null || form.getNumero() <= 0) {
            throw new IllegalArgumentException("El número de jornada debe ser un entero positivo.");
        }
        if (form.getTemporadaId() == null) {
            throw new IllegalArgumentException("La temporada es requerida.");
        }
    }

    private void validarRango(LocalDate desde, LocalDate hasta) {
        if (hasta.isBefore(desde)) {
            throw new IllegalArgumentException("Rango de fechas inválido: 'hasta' es anterior a 'desde'.");
        }
    }

    private boolean equalsId(Object entity, Long id) {
        if (entity == null || id == null) return entity == null && id == null;
        try {
            Long currentId = (Long) entity.getClass().getMethod("getId").invoke(entity);
            return id.equals(currentId);
        } catch (Exception ignored) {
            return false;
        }
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
