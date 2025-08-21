// src/main/java/com/futvia/repository/partido/TernaArbitralRepository.java
package com.futvia.repository.partido;

import com.futvia.model.partido.TernaArbitral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TernaArbitralRepository extends JpaRepository<TernaArbitral, Long> {
    // Ya existente
    Optional<TernaArbitral> findByPartido_Id(Long partidoId);

    // Validar 1:1 (evitar más de una terna por partido)
    boolean existsByPartido_Id(Long partidoId);
}
