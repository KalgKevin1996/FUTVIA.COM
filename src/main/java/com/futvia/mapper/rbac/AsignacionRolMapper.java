package com.futvia.mapper.rbac;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.rbac.*;
import com.futvia.dto.rbac.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface AsignacionRolMapper {
    @Mappings({
            @Mapping(target = "usuarioId", source = "usuario.id"),
            @Mapping(target = "rolId", source = "rol.id")
    })
    AsignacionRolDTO toDto(AsignacionRol e);

    @Mappings({
            @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = {}),
            @Mapping(target = "rol", source = "rolId")
    })
    AsignacionRol toEntity(AsignacionRolFormDTO f);

    void update(@MappingTarget AsignacionRol e, AsignacionRolFormDTO f);
}
