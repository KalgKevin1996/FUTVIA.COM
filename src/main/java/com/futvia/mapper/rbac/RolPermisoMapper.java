package com.futvia.mapper.rbac;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.rbac.*;
import com.futvia.dto.rbac.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface RolPermisoMapper {
    @Mappings({
            @Mapping(target = "rolId", source = "rol.id"),
            @Mapping(target = "permisoId", source = "permiso.id")
    })
    RolPermisoDTO toDto(RolPermiso e);
}