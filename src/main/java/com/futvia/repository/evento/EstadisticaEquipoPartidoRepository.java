// src/main/java/com/futvia/repository/evento/EstadisticaEquipoPartidoRepository.java
package com.futvia.repository.evento;

import com.futvia.model.evento.EstadisticaEquipoPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadisticaEquipoPartidoRepository extends JpaRepository<EstadisticaEquipoPartido, Long> {

    Optional<EstadisticaEquipoPartido> findByPartido_IdAndEquipo_Id(Long partidoId, Long equipoId);

    // --- NUEVOS ---
    List<EstadisticaEquipoPartido> findByPartido_Id(Long partidoId);
    List<EstadisticaEquipoPartido> findByEquipo_Id(Long equipoId);
}
