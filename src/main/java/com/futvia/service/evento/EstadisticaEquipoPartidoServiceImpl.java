package com.futvia.service.evento;

import com.futvia.dto.evento.EstadisticaEquipoPartidoDTO;
import com.futvia.dto.evento.EstadisticaEquipoPartidoFormDTO;
import com.futvia.mapper.evento.EstadisticaEquipoPartidoMapper;
import com.futvia.model.evento.EstadisticaEquipoPartido;
import com.futvia.repository.evento.EstadisticaEquipoPartidoRepository;
import com.futvia.repository.partido.PartidoRepository;
import com.futvia.repository.club.EquipoRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticaEquipoPartidoServiceImpl implements EstadisticaEquipoPartidoService {

    private final EstadisticaEquipoPartidoRepository repo;
    private final PartidoRepository partidoRepo;
    private final EquipoRepository equipoRepo;
    private final EstadisticaEquipoPartidoMapper mapper;

    @Transactional
    @Override
    public EstadisticaEquipoPartidoDTO guardarOActualizar(EstadisticaEquipoPartidoFormDTO form) {
        var partido = partidoRepo.findById(form.getPartidoId())
                .orElseThrow(() -> new NotFoundException("Partido no existe"));
        var equipo = equipoRepo.findById(form.getEquipoId())
                .orElseThrow(() -> new NotFoundException("Equipo no existe"));

        EstadisticaEquipoPartido entidad = repo.findByPartido_IdAndEquipo_Id(form.getPartidoId(), form.getEquipoId())
                .orElseGet(() -> {
                    var nue = new EstadisticaEquipoPartido();
                    nue.setPartido(partido);
                    nue.setEquipo(equipo);
                    return nue;
                });

        // CORRECCIÓN: usa la firma real del mapper
        mapper.update(entidad, form);

        return mapper.toDto(repo.save(entidad));
    }

    @Override
    public EstadisticaEquipoPartidoDTO obtener(Long partidoId, Long equipoId) {
        var entidad = repo.findByPartido_IdAndEquipo_Id(partidoId, equipoId)
                .orElseThrow(() -> new NotFoundException("No hay estadísticas para (partido, equipo)"));
        return mapper.toDto(entidad);
    }

    @Override
    public List<EstadisticaEquipoPartidoDTO> listarPorPartido(Long partidoId) {
        return repo.findByPartido_Id(partidoId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Estadística no existe"));
        repo.delete(e);
    }
}
