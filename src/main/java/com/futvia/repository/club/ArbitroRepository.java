// src/main/java/com/futvia/repository/club/ArbitroRepository.java
package com.futvia.repository.club;

import com.futvia.model.club.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long> {
    // Búsquedas frecuentes
    Optional<Arbitro> findByUsuario_Id(Long usuarioId);   // vínculo opcional a Usuario :contentReference[oaicite:16]{index=16}
    List<Arbitro> findByNivelContainingIgnoreCase(String q);
    List<Arbitro> findByAsociacionContainingIgnoreCase(String q);
}
