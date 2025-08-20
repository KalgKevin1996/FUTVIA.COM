// src/main/java/com/futvia/repository/club/JugadorRepository.java
package com.futvia.repository.club;

import com.futvia.model.club.Jugador;
import com.futvia.model.club.enums.Posicion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String n, String a); // existente
    Page<Jugador> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String n, String a, Pageable pageable);

    List<Jugador> findByPosicion(Posicion posicion); // filtro por posición :contentReference[oaicite:10]{index=10}
    List<Jugador> findByPerfilPublicoTrue();          // listado público

    // Vinculado a usuario (opcional)
    Optional<Jugador> findByUsuario_Id(Long usuarioId);
    boolean existsByUsuario_Id(Long usuarioId);       // :contentReference[oaicite:11]{index=11}

    // Autocomplete ligero
    List<Jugador> findTop10ByNombreStartingWithIgnoreCase(String prefijo);
}
