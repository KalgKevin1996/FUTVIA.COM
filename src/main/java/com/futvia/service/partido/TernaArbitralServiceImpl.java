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
class TernaArbitralServiceImpl implements TernaArbitralService {

    private final TernaArbitralRepository ternaRepo;
    private final TernaDetalleRepository detalleRepo;
    private final PartidoRepository partidoRepo;
    private final com.futvia.repository.club.ArbitroRepository arbitroRepo;
    private final TernaArbitralMapper ternaMapper;
    private final TernaDetalleMapper detalleMapper;

    TernaArbitralServiceImpl(
            TernaArbitralRepository ternaRepo,
            TernaDetalleRepository detalleRepo,
            PartidoRepository partidoRepo,
            com.futvia.repository.club.ArbitroRepository arbitroRepo,
            TernaArbitralMapper ternaMapper,
            TernaDetalleMapper detalleMapper
    ) {
        this.ternaRepo = ternaRepo;
        this.detalleRepo = detalleRepo;
        this.partidoRepo = partidoRepo;
        this.arbitroRepo = arbitroRepo;
        this.ternaMapper = ternaMapper;
        this.detalleMapper = detalleMapper;
    }

    @Override
    public TernaArbitralDTO crearParaPartido(Long partidoId) {
        Partido partido = partidoRepo.findById(partidoId).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        ternaRepo.findByPartido_Id(partidoId).ifPresent(t -> { throw new IllegalStateException("El partido ya tiene terna arbitral"); });

        TernaArbitral t = TernaArbitral.builder().partido(partido).build();
        t = ternaRepo.save(t);
        return ternaMapper.toDto(t);
    }

    @Override
    @Transactional(readOnly = true)
    public TernaArbitralDTO obtenerPorPartido(Long partidoId) {
        TernaArbitral t = ternaRepo.findByPartido_Id(partidoId).orElseThrow(() -> new NotFoundException("Terna no encontrada para el partido"));
        return ternaMapper.toDto(t);
    }

    @Override
    public TernaDetalleDTO agregarArbitro(Long ternaId, Long arbitroId, com.futvia.model.common.enums.RolArbitral rol) {
        TernaArbitral t = ternaRepo.findById(ternaId).orElseThrow(() -> new NotFoundException("Terna no encontrada"));
        var arbitro = arbitroRepo.findById(arbitroId).orElseThrow(() -> new NotFoundException("Árbitro no encontrado"));

        // Restricción única (terna, arbitro, rol) definida en la entidad; confía en constraint para duplicados.
        TernaDetalle d = TernaDetalle.builder().terna(t).arbitro(arbitro).rol(rol).build();
        d = detalleRepo.save(d);
        return detalleMapper.toDto(d, new com.futvia.mapper.RefMapper() {}); // MapStruct @Context si tu mapper lo requiere
    }

    @Override
    public void quitarDetalle(Long ternaDetalleId) {
        if (!detalleRepo.existsById(ternaDetalleId)) throw new NotFoundException("Detalle de terna no encontrado");
        detalleRepo.deleteById(ternaDetalleId);
    }
}