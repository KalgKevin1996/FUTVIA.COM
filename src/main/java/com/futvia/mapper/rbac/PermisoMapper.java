package com.futvia.mapper.rbac;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.rbac.*;
import com.futvia.dto.rbac.*;

@Mapper(config = MapStructConfig.class)
public interface PermisoMapper {
    PermisoDTO toDto(Permiso e);
    Permiso toEntity(PermisoFormDTO f);
    void update(@MappingTarget Permiso e, PermisoFormDTO f);
}