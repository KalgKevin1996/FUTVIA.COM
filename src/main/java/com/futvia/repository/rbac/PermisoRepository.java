// src/main/java/com/futvia/repository/rbac/PermisoRepository.java
package com.futvia.repository.rbac;

import com.futvia.model.rbac.Permiso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Optional<Permiso> findByCodigoIgnoreCase(String codigo);                        // código único :contentReference[oaicite:7]{index=7}
    boolean existsByCodigoIgnoreCase(String codigo);

    // Búsquedas por prefijo y contiene (útil para autocompletar/administración)
    Page<Permiso> findByCodigoStartingWithIgnoreCase(String prefijo, Pageable p);
    Page<Permiso> findByCodigoContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
            String q1, String q2, Pageable p);

    // Lotes
    List<Permiso> findByCodigoIn(Collection<String> codigos);
}
