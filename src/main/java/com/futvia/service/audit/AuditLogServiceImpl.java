// src/main/java/com/futvia/service/audit/AuditLogServiceImpl.java
package com.futvia.service.audit;

import com.futvia.dto.audit.AuditLogDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.audit.AuditLogMapper;
import com.futvia.model.audit.AuditLog;
import com.futvia.repository.audit.AuditLogRepository;
import com.futvia.repository.rbac.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final AuditLogMapper mapper;
    private final RefMapper ref;

    @Override
    public Page<AuditLogDTO> buscar(String entidad, Long entidadId,
                                    Long usuarioId, String accion,
                                    LocalDateTime desde, LocalDateTime hasta,
                                    String q, Pageable pageable) {

        Page<AuditLog> page;

        // 1) Elegimos la consulta más específica del repo según los filtros presentes
        if (entidad != null && entidadId != null) {
            page = repo.findByEntidadAndEntidadIdOrderByCreatedAtDesc(entidad, entidadId, pageable);
        } else if (usuarioId != null && desde != null && hasta != null) {
            page = repo.findByUsuario_IdAndCreatedAtBetween(usuarioId, desde, hasta, pageable);
        } else if (desde != null && hasta != null) {
            page = repo.findByCreatedAtBetween(desde, hasta, pageable);
        } else if (usuarioId != null) {
            page = repo.findByUsuario_Id(usuarioId, pageable);
        } else if (accion != null) {
            page = repo.findByAccion(accion, pageable);
        } else if (q != null && !q.isBlank()) {
            page = repo.searchByDetalleOrIp(q.trim(), pageable);
        } else {
            page = repo.findAll(pageable);
        }

        // 2) Filtros residuales en memoria (SIN tocar createdAt)
        var filtered = page.getContent().stream()
                .filter(a -> accion == null || accion.equals(a.getAccion()))
                .filter(a -> usuarioId == null || (a.getUsuario() != null && usuarioId.equals(a.getUsuario().getId())))
                .filter(a -> entidad == null || entidad.equals(a.getEntidad()))
                .filter(a -> entidadId == null || entidadId.equals(a.getEntidadId()))
                .toList();

        var out = filtered.stream().map(e -> mapper.toDto(e, ref)).toList();
        // conservamos la paginación original (total de DB)
        return new PageImpl<>(out, pageable, page.getTotalElements());
    }

    @Override
    public List<AuditLogDTO> ultimosDeEntidad(String entidad, Long entidadId, int limit) {
        int top = (limit <= 0 || limit > 100) ? 20 : limit;
        return repo.findTop20ByEntidadAndEntidadIdOrderByCreatedAtDesc(entidad, entidadId)
                .stream()
                .limit(top)
                .map(e -> mapper.toDto(e, ref))
                .toList();
    }

    @Override
    public AuditLogDTO registrar(String accion, String entidad, Long entidadId, Long usuarioId, String detalle, String ip) {
        var log = new AuditLog();
        log.setAccion(accion);
        log.setEntidad(entidad);
        log.setEntidadId(entidadId);
        log.setDetalle(detalle);
        log.setIp(ip);
        if (usuarioId != null) {
            log.setUsuario(usuarioRepo.findById(usuarioId).orElse(null));
        }
        return mapper.toDto(repo.save(log), ref);
    }
}
