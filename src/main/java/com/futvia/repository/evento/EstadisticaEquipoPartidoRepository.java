package com.futvia.repository.evento;

import com.futvia.model.evento.EstadisticaEquipoPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EstadisticaEquipoPartidoRepository extends JpaRepository<EstadisticaEquipoPartido, Long> {
    Optional<EstadisticaEquipoPartido> findByPartido_IdAndEquipo_Id(Long partidoId, Long equipoId);
}