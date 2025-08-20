package com.futvia.mapper.partido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.partido.*;
import com.futvia.model.club.*;
import com.futvia.dto.partido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface TernaDetalleMapper {
    @Mappings({
            @Mapping(target = "ternaId", source = "terna.id"),
            @Mapping(target = "arbitroId", source = "arbitro.id"),
            @Mapping(target = "arbitroNombreVisible", expression = "java(ref.nombreCompleto(e.getArbitro().getUsuario()))")
    })
    TernaDetalleDTO toDto(TernaDetalle e, @Context RefMapper ref);

    @Mappings({
            @Mapping(target = "terna", source = "ternaId"),
            @Mapping(target = "arbitro", source = "arbitroId")
    })
    TernaDetalle toEntity(TernaDetalleFormDTO f);
}
