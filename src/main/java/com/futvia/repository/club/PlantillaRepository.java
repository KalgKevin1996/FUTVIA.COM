// src/main/java/com/futvia/repository/club/PlantillaRepository.java
package com.futvia.repository.club;

import com.futvia.model.club.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {
    List<Plantilla> findByEquipo_IdAndTemporada_Id(Long equipoId, Long temporadaId); // existente
    List<Plantilla> findByJugador_IdAndTemporada_Id(Long jugadorId, Long temporadaId);
    long countByEquipo_IdAndTemporada_Id(Long equipoId, Long temporadaId);

    // Validación rápida de la restricción única (equipo, jugador, temporada)
    boolean existsByEquipo_IdAndJugador_IdAndTemporada_Id(Long equipoId, Long jugadorId, Long temporadaId); // :contentReference[oaicite:12]{index=12}

    // Limpiezas por reprogramación de temporada del equipo
    long deleteByEquipo_IdAndTemporada_Id(Long equipoId, Long temporadaId);
}
