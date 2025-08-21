// src/main/java/com/futvia/repository/competicion/CategoriaRepository.java
package com.futvia.repository.competicion;

import com.futvia.model.competicion.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // existente
    List<Categoria> findByCompeticion_Id(Long competicionId);

    // variantes
    Page<Categoria> findByCompeticion_Id(Long competicionId, Pageable pageable);
    Page<Categoria> findByCompeticion_IdAndNombreContainingIgnoreCase(Long competicionId, String q, Pageable pageable);

    // constraint único (competicion_id, nombre)
    boolean existsByCompeticion_IdAndNombreIgnoreCase(Long competicionId, String nombre);
}
