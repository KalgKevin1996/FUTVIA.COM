package com.futvia.mapper.competicion;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.competicion.*;
import com.futvia.model.geo.*;
import com.futvia.model.contenido.*;
import com.futvia.dto.competicion.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface CompeticionMapper {
    @Mappings({
            @Mapping(target = "organizadorId", source = "organizador.id"),
            @Mapping(target = "organizadorNombre", source = "organizador.nombre"),
            @Mapping(target = "reglamentoArchivoId", source = "reglamentoPdf.id"),
            @Mapping(target = "paisId", source = "pais.id"),
            @Mapping(target = "paisNombre", source = "pais.nombre"),
            @Mapping(target = "departamentoId", source = "departamento.id"),
            @Mapping(target = "departamentoNombre", source = "departamento.nombre"),
            @Mapping(target = "municipioId", source = "municipio.id"),
            @Mapping(target = "municipioNombre", source = "municipio.nombre"),
            @Mapping(target = "zonaId", source = "zona.id"),
            @Mapping(target = "zonaNumero", source = "zona.numero")
    })
    CompeticionDTO toDto(Competicion e);

    @Mappings({
            @Mapping(target = "organizador", source = "organizadorId"),
            @Mapping(target = "reglamentoPdf", source = "reglamentoArchivoId"),
            @Mapping(target = "pais", source = "paisId"),
            @Mapping(target = "departamento", source = "departamentoId"),
            @Mapping(target = "municipio", source = "municipioId"),
            @Mapping(target = "zona", source = "zonaId")
    })
    Competicion toEntity(CompeticionFormDTO f);

    void update(@MappingTarget Competicion e, CompeticionFormDTO f);
}
