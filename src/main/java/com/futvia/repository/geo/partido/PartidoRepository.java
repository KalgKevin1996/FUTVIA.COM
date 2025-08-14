package com.futvia.repository.geo.partido;

import com.futvia.model.partido.*;
import com.futvia.model.common.enums.EstadoPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.*;
import java.util.*;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    List<Partido> findByTemporada_IdAndEstadoOrderByFechaHoraAsc(Long temporadaId, EstadoPartido estado);
    List<Partido> findTop20ByEstadoAndFechaHoraAfterOrderByFechaHoraAsc(EstadoPartido estado, LocalDateTime fechaDesde);
}