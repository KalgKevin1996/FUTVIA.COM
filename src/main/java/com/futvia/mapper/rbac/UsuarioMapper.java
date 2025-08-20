package com.futvia.mapper.rbac;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.rbac.*;
import com.futvia.dto.rbac.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario e);
    Usuario toEntity(UsuarioFormDTO f);
    void update(@MappingTarget Usuario e, UsuarioFormDTO f);
}
