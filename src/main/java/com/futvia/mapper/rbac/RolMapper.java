package com.futvia.mapper.rbac;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.rbac.*;
import com.futvia.dto.rbac.*;

@Mapper(config = MapStructConfig.class)
public interface RolMapper {
    RolDTO toDto(Rol e);
    Rol toEntity(RolFormDTO f);
    void update(@MappingTarget Rol e, RolFormDTO f);
}
