// src/main/java/com/futvia/repository/competicion/OrganizadorRepository.java
package com.futvia.repository.competicion;

import com.futvia.model.competicion.Organizador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    Optional<Organizador> findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);

    Page<Organizador> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    List<Organizador> findTop10ByNombreStartingWithIgnoreCase(String prefijo);

    // filtro por tipo (campo String: federacion, asociacion, privado, etc.)
    Page<Organizador> findByTipoIgnoreCase(String tipo, Pageable pageable);
}
