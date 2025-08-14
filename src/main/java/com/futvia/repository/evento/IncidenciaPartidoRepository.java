package com.futvia.repository.evento;

import com.futvia.model.evento.*;
import com.futvia.model.common.enums.TipoIncidencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface IncidenciaPartidoRepository extends JpaRepository<IncidenciaPartido, Long> {
    long countByTipoAndPartido_IdAndEquipo_Id(TipoIncidencia tipo, Long partidoId, Long equipoId);

    @Query("select count(i) from IncidenciaPartido i where i.partido.id=:partidoId and i.tipo = com.futvia.model.common.enums.TipoIncidencia.AUTOGOL and i.equipo.id <> :equipoId")
    long countAutogolesRival(@Param("partidoId") Long partidoId, @Param("equipoId") Long equipoId);

    @Query("select count(i) from IncidenciaPartido i join i.partido p where i.tipo = com.futvia.model.common.enums.TipoIncidencia.GOL and i.jugadorPrincipal.id = :jugadorId and p.temporada.id = :temporadaId")
    long countGolesJugadorEnTemporada(@Param("jugadorId") Long jugadorId, @Param("temporadaId") Long temporadaId);
}