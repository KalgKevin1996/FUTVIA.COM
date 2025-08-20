// src/main/java/com/futvia/repository/rbac/RolRepository.java
package com.futvia.repository.rbac;

import com.futvia.model.common.enums.RolNombre;
import com.futvia.model.rbac.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(RolNombre nombre);                                  // nombre único (enum) :contentReference[oaicite:6]{index=6}
    boolean existsByNombre(RolNombre nombre);

    // Jerarquía por nivel (campo Integer nivel)
    List<Rol> findByNivelGreaterThanEqual(Integer nivel);
    List<Rol> findByNivelBetween(Integer min, Integer max);
}
