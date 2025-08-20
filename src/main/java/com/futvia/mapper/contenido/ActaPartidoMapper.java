package com.futvia.mapper.contenido;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.contenido.*;
import com.futvia.model.partido.*;
import com.futvia.dto.contenido.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface ActaPartidoMapper {
    @Mappings({
            @Mapping(target = "partidoId", source = "partido.id"),
            @Mapping(target = "partidoResumen", expression = "java(resumen(e.getPartido()))"),
            @Mapping(target = "archivoId", source = "archivo.id")
    })
    ActaPartidoDTO toDto(ActaPartido e);

    @Mappings({
            @Mapping(target = "partido", source = "partidoId"),
            @Mapping(target = "archivo", source = "archivoId")
    })
    ActaPartido toEntity(ActaPartidoFormDTO f);

    default String resumen(Partido p) {
        if (p == null) return null;
        var l = p.getEquipoLocal() != null ? p.getEquipoLocal().getNombreVisible() : "?";
        var v = p.getEquipoVisitante() != null ? p.getEquipoVisitante().getNombreVisible() : "?";
        var fh = p.getFechaHora() != null ? p.getFechaHora().toString() : "";
        return l + " vs " + v + (fh.isEmpty() ? "" : (" (" + fh + ")"));
    }
}
