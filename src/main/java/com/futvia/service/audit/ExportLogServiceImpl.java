// src/main/java/com/futvia/service/audit/ExportLogServiceImpl.java
package com.futvia.service.audit;

import com.futvia.dto.audit.ExportLogDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.audit.ExportLogMapper;
import com.futvia.model.audit.ExportLog;
import com.futvia.repository.audit.ExportLogRepository;
import com.futvia.repository.contenido.ArchivoRepository;
import com.futvia.repository.rbac.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExportLogServiceImpl implements ExportLogService {

    private final ExportLogRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final ArchivoRepository archivoRepo;
    private final ExportLogMapper mapper;
    private final RefMapper ref;

    @Override
    public Page<ExportLogDTO> buscar(Long usuarioId, String tipo, LocalDateTime desde, LocalDateTime hasta, Pageable pageable) {
        Page<ExportLog> page;
        if (usuarioId != null && desde != null && hasta != null) {
            page = repo.findByUsuario_IdAndCreatedAtBetween(usuarioId, desde, hasta, pageable);
        } else if (desde != null && hasta != null) {
            page = repo.findByCreatedAtBetween(desde, hasta, pageable);
        } else if (usuarioId != null) {
            page = repo.findByUsuario_Id(usuarioId, pageable);
        } else if (tipo != null) {
            page = repo.findByTipo(tipo, pageable);
        } else {
            page = repo.findAll(pageable);
        }

        var out = page.map(e -> mapper.toDto(e, ref));
        return new PageImpl<>(out.getContent(), pageable, out.getTotalElements());
    }

    @Override
    public ExportLogDTO registrar(String tipo, Long usuarioId, Long archivoId, String parametrosJson) {
        var e = new ExportLog();
        e.setTipo(tipo);
        e.setParametros(parametrosJson);

        if (usuarioId != null) {
            e.setUsuario(usuarioRepo.findById(usuarioId).orElse(null));
        }
        if (archivoId != null) {
            e.setArchivo(archivoRepo.findById(archivoId).orElse(null));
        }
        return mapper.toDto(repo.save(e), ref);
    }

    @Override
    public Optional<ExportLogDTO> ultimoPorArchivo(Long archivoId) {
        return repo.findTop1ByArchivo_IdOrderByCreatedAtDesc(archivoId)
                .map(e -> mapper.toDto(e, ref));
    }
}
