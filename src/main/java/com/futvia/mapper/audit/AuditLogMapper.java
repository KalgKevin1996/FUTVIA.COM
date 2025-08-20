package com.futvia.mapper.audit;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.audit.*;
import com.futvia.dto.audit.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface AuditLogMapper {
    @Mappings({
            @Mapping(target = "usuarioId", source = "usuario.id"),
            @Mapping(target = "usuarioNombre", expression = "java(ref.nombreCompleto(e.getUsuario()))")
    })
    AuditLogDTO toDto(AuditLog e, @Context RefMapper ref);
}