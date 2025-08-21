// src/main/java/com/futvia/service/contenido/ActaPartidoServiceImpl.java
package com.futvia.service.contenido;

import com.futvia.dto.contenido.ActaPartidoDTO;
import com.futvia.dto.contenido.ActaPartidoFormDTO;
import com.futvia.mapper.contenido.ActaPartidoMapper;
import com.futvia.model.contenido.ActaPartido;
import com.futvia.repository.contenido.ActaPartidoRepository;
import com.futvia.service.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActaPartidoServiceImpl implements ActaPartidoService {

    private final ActaPartidoRepository repo;
    private final ActaPartidoMapper mapper;

    @Override
    public Page<ActaPartidoDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public java.util.Optional<ActaPartidoDTO> obtenerPorPartido(Long partidoId) {
        return repo.findByPartido_Id(partidoId).map(mapper::toDto);
    }

    @Override
    public ActaPartidoDTO obtener(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Acta no encontrada"));
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public ActaPartidoDTO crear(ActaPartidoFormDTO form) {
        // Regla: un partido debe tener como máximo un acta
        var existente = repo.findByPartido_Id(form.getPartidoId());
        if (existente.isPresent()) {
            throw new IllegalStateException("El partido ya tiene un acta registrada");
        }
        ActaPartido e = mapper.toEntity(form);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public ActaPartidoDTO editar(Long id, ActaPartidoFormDTO form) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Acta no encontrada"));
        // Si cambias el partido, vuelve a verificar unicidad (evita duplicados)
        if (form.getPartidoId() != null) {
            var otro = repo.findByPartido_Id(form.getPartidoId());
            if (otro.isPresent() && !otro.get().getId().equals(id)) {
                throw new IllegalStateException("Ese partido ya tiene otra acta");
            }
        }
        // MapStruct no tiene update para Acta en tu mapper, así que reasignamos directo:
        if (form.getPartidoId() != null) {
            var p = new com.futvia.model.partido.Partido();
            p.setId(form.getPartidoId());
            e.setPartido(p);
        }
        if (form.getArchivoId() != null) {
            var a = new com.futvia.model.contenido.Archivo();
            a.setId(form.getArchivoId());
            e.setArchivo(a);
        }
        if (form.getFirmantes() != null) e.setFirmantes(form.getFirmantes());
        return mapper.toDto(repo.save(e));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Acta no encontrada"));
        repo.delete(e);
    }
}
