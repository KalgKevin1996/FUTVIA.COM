// src/main/java/com/futvia/repository/club/EquipoRepository.java
package com.futvia.repository.club;

import com.futvia.model.club.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByCompeticion_Id(Long competicionId);           // :contentReference[oaicite:4]{index=4}
    List<Equipo> findByCategoria_Id(Long categoriaId);               // :contentReference[oaicite:5]{index=5}
    List<Equipo> findByClub_Id(Long clubId);                          // relación Equipo.club :contentReference[oaicite:6]{index=6}
    Page<Equipo> findByCompeticion_Id(Long competicionId, Pageable pageable);
    Page<Equipo> findByCategoria_Id(Long categoriaId, Pageable pageable);

    // Para evitar duplicados visibles dentro de un club
    boolean existsByClub_IdAndNombreVisibleIgnoreCase(Long clubId, String nombreVisible); // :contentReference[oaicite:7]{index=7}

    // Filtros combinados útiles en listados
    List<Equipo> findByCompeticion_IdAndCategoria_Id(Long competicionId, Long categoriaId); // :contentReference[oaicite:8]{index=8}
    Page<Equipo> findByNombreVisibleContainingIgnoreCaseAndCompeticion_Id(String q, Long competicionId, Pageable pageable); // :contentReference[oaicite:9]{index=9}

    long countByClub_Id(Long clubId); // métricas rápidas por club
}
