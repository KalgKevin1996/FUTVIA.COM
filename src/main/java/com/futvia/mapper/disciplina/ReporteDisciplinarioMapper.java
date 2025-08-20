package com.futvia.mapper.disciplina;

import org.mapstruct.*;
import com.futvia.mapper.*;
import com.futvia.model.disciplina.*;
import com.futvia.model.partido.*;
import com.futvia.dto.disciplina.*;

@Mapper(config = MapStructConfig.class, uses = RefMapper.class)
public interface ReporteDisciplinarioMapper {
    @Mappings({
            @Mapping(target = "partidoId", source = "partido.id"),
            @Mapping(target = "partidoResumen", expression = "java(resumen(e.getPartido()))"),
            @Mapping(target = "reportanteId", source = "reportante.id"),
            @Mapping(target = "reportanteNombre", expression = "java(ref.nombreCompleto(e.getReportante()))")
    })
    ReporteDisciplinarioDTO toDto(ReporteDisciplinario e, @Context RefMapper ref);

    @Mappings({
            @Mapping(target = "partido", source = "partidoId"),
            @Mapping(target = "reportante", source = "reportanteId")
    })
    ReporteDisciplinario toEntity(ReporteDisciplinarioFormDTO f);

    default String resumen(Partido p) {
        if (p == null) return null;
        var l = p.getEquipoLocal() != null ? p.getEquipoLocal().getNombreVisible() : "?";
        var v = p.getEquipoVisitante() != null ? p.getEquipoVisitante().getNombreVisible() : "?";
        return l + " vs " + v;
    }
}
