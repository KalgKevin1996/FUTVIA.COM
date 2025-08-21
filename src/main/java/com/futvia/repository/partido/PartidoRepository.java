// src/main/java/com/futvia/repository/partido/PartidoRepository.java
package com.futvia.repository.partido;

import com.futvia.model.common.enums.EstadoPartido;
import com.futvia.model.partido.Partido;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PartidoRepository extends JpaRepository<Partido, Long>, JpaSpecificationExecutor<Partido> {

    // Ya existentes
    List<Partido> findByTemporada_IdAndEstadoOrderByFechaHoraAsc(Long temporadaId, EstadoPartido estado);
    List<Partido> findTop20ByEstadoAndFechaHoraAfterOrderByFechaHoraAsc(EstadoPartido estado, LocalDateTime fechaDesde);

    // Calendario por categoría/jornada
    List<Partido> findByTemporada_IdAndCategoria_IdOrderByFechaHoraAsc(Long temporadaId, Long categoriaId);
    List<Partido> findByJornada_IdOrderByFechaHoraAsc(Long jornadaId);

    // Vistas de equipo (fixture e historial)
    Page<Partido> findByTemporada_IdAndEquipoLocal_IdOrTemporada_IdAndEquipoVisitante_Id(
            Long temporadaId1, Long equipoIdLocal, Long temporadaId2, Long equipoIdVisitante, Pageable pageable);

    // Head-to-head
    @Query("""
           select p from Partido p
           where p.temporada.id = :temporadaId
             and ((p.equipoLocal.id = :equipoA and p.equipoVisitante.id = :equipoB)
               or (p.equipoLocal.id = :equipoB and p.equipoVisitante.id = :equipoA))
           order by p.fechaHora desc
           """)
    List<Partido> findHeadToHead(Long temporadaId, Long equipoA, Long equipoB, Pageable topN);

    // Choques/solapamientos (control de agenda)
    boolean existsByEstadio_IdAndFechaHoraBetween(Long estadioId, LocalDateTime inicio, LocalDateTime fin);

    @Query("""
           select case when count(p)>0 then true else false end
           from Partido p
           where p.fechaHora between :inicio and :fin
             and (p.equipoLocal.id = :equipoId or p.equipoVisitante.id = :equipoId)
           """)
    boolean equipoTienePartidoEnVentana(Long equipoId, LocalDateTime inicio, LocalDateTime fin);

    // Próximos/recientes por estado con paginado
    Page<Partido> findByEstadoAndFechaHoraAfter(EstadoPartido estado, LocalDateTime desde, Pageable pageable);
    Page<Partido> findByEstadoAndFechaHoraBefore(EstadoPartido estado, LocalDateTime hasta, Pageable pageable);

    // Atajos para cierre/marcador final
    Optional<Partido> findFirstByTemporada_IdAndEstadoOrderByFechaHoraAsc(Long temporadaId, EstadoPartido estado);
}
