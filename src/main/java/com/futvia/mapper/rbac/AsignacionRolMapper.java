package com.futvia.mapper.rbac;

import com.futvia.dto.rbac.AsignacionRolDTO;
import com.futvia.dto.rbac.AsignacionRolFormDTO;
import com.futvia.model.rbac.AsignacionRol;
import com.futvia.model.rbac.Rol;
import com.futvia.model.rbac.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AsignacionRolMapper {

    @Mappings({
            @Mapping(target = "usuarioId", source = "usuario.id"),
            @Mapping(target = "rolId",     source = "rol.id")
    })
    AsignacionRolDTO toDto(AsignacionRol e);

    @Mappings({
            @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "refUsuario"),
            @Mapping(target = "rol",     source = "rolId",     qualifiedByName = "refRol")
    })
    AsignacionRol toEntity(AsignacionRolFormDTO f);

    void update(@MappingTarget AsignacionRol e, AsignacionRolFormDTO f);

    @Named("refUsuario")
    default Usuario refUsuario(Long id){
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }

    @Named("refRol")
    default Rol refRol(Long id){
        if (id == null) return null;
        Rol r = new Rol();
        r.setId(id);
        return r;
    }
}
