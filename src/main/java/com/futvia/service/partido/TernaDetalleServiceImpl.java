// src/main/java/com/futvia/service/partido/impl/TernaDetalleServiceImpl.java
package com.futvia.service.partido;

import com.futvia.dto.partido.TernaDetalleDTO;
import com.futvia.dto.partido.TernaDetalleFormDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.partido.TernaDetalleMapper;
import com.futvia.model.club.Arbitro;
import com.futvia.model.common.enums.RolArbitral;
import com.futvia.model.partido.TernaArbitral;
import com.futvia.model.partido.TernaDetalle;
import com.futvia.repository.club.ArbitroRepository;
import com.futvia.repository.partido.TernaArbitralRepository;
import com.futvia.repository.partido.TernaDetalleRepository;
import com.futvia.service.partido.TernaDetalleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TernaDetalleServiceImpl implements TernaDetalleService {

    private final TernaDetalleRepository detalleRepo;
    private final TernaArbitralRepository ternaRepo;
    private final ArbitroRepository arbitroRepo;
    private final TernaDetalleMapper mapper;
    private final RefMapper ref;

    public TernaDetalleServiceImpl(
            TernaDetalleRepository detalleRepo,
            TernaArbitralRepository ternaRepo,
            ArbitroRepository arbitroRepo,
            TernaDetalleMapper mapper,
            RefMapper ref
    ) {
        this.detalleRepo = detalleRepo;
        this.ternaRepo = ternaRepo;
        this.arbitroRepo = arbitroRepo;
        this.mapper = mapper;
        this.ref = ref;
    }

    @Override
    public TernaDetalleDTO obtener(Long id) {
        var e = detalleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TernaDetalle no encontrado: id=" + id));
        return mapper.toDto(e, ref);
    }

    @Override
    public List<TernaDetalleDTO> listarPorTerna(Long ternaId) {
        return detalleRepo.findByTerna_Id(ternaId)
                .stream()
                .map(d -> mapper.toDto(d, ref))
                .toList();
    }

    @Override
    public TernaDetalleDTO crear(TernaDetalleFormDTO form) {
        validarUnicidad(form.getTernaId(), form.getArbitroId(), form.getRol(), null);

        TernaArbitral terna = ternaRepo.findById(form.getTernaId())
                .orElseThrow(() -> new IllegalArgumentException("TernaArbitral no encontrada: id=" + form.getTernaId()));
        Arbitro arbitro = arbitroRepo.findById(form.getArbitroId())
                .orElseThrow(() -> new IllegalArgumentException("Árbitro no encontrado: id=" + form.getArbitroId()));

        TernaDetalle nuevo = TernaDetalle.builder()
                .terna(terna)
                .arbitro(arbitro)
                .rol(form.getRol())
                .build();

        var guardado = detalleRepo.save(nuevo);
        return mapper.toDto(guardado, ref);
    }

    @Override
    public TernaDetalleDTO actualizar(Long id, TernaDetalleFormDTO form) {
        TernaDetalle actual = detalleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TernaDetalle no encontrado: id=" + id));

        // Si no cambia la combinación (terna, árbitro, rol), no hace falta validar unicidad
        boolean sinCambiosClave =
                equalsId(actual.getTerna(), form.getTernaId()) &&
                        equalsId(actual.getArbitro(), form.getArbitroId()) &&
                        actual.getRol() == form.getRol();

        if (!sinCambiosClave) {
            validarUnicidad(form.getTernaId(), form.getArbitroId(), form.getRol(), id);
        }

        // Reasignar referencias si cambiaron
        if (!equalsId(actual.getTerna(), form.getTernaId())) {
            TernaArbitral terna = ternaRepo.findById(form.getTernaId())
                    .orElseThrow(() -> new IllegalArgumentException("TernaArbitral no encontrada: id=" + form.getTernaId()));
            actual.setTerna(terna);
        }
        if (!equalsId(actual.getArbitro(), form.getArbitroId())) {
            Arbitro arbitro = arbitroRepo.findById(form.getArbitroId())
                    .orElseThrow(() -> new IllegalArgumentException("Árbitro no encontrado: id=" + form.getArbitroId()));
            actual.setArbitro(arbitro);
        }
        actual.setRol(form.getRol());

        var guardado = detalleRepo.save(actual);
        return mapper.toDto(guardado, ref);
    }

    @Override
    public void eliminar(Long id) {
        var e = detalleRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TernaDetalle no encontrado: id=" + id));
        detalleRepo.delete(e);
    }

    // ---------- Helpers ----------

    private void validarUnicidad(Long ternaId, Long arbitroId, RolArbitral rol, Long excluirId) {
        // Si estamos en update y la combinación ya pertenece al mismo registro, permitir.
        if (excluirId != null) {
            // Cargar todos de la terna y verificar choque con otro id
            boolean existeOtro = detalleRepo.findByTerna_Id(ternaId).stream()
                    .anyMatch(d -> d.getArbitro() != null
                            && d.getArbitro().getId().equals(arbitroId)
                            && d.getRol() == rol
                            && !d.getId().equals(excluirId));
            if (existeOtro) {
                throw new IllegalStateException("Ya existe un detalle con la combinación (terna, árbitro, rol).");
            }
        } else {
            // Crear: basta con verificar existencia directa
            boolean existe = detalleRepo
                    .existsByTerna_IdAndArbitro_IdAndRol(ternaId, arbitroId, rol);
            if (existe) {
                throw new IllegalStateException("Ya existe un detalle con la combinación (terna, árbitro, rol).");
            }
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
}
