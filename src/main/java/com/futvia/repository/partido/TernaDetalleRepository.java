// src/main/java/com/futvia/repository/partido/TernaDetalleRepository.java
package com.futvia.repository.partido;

import com.futvia.model.partido.TernaDetalle;
import com.futvia.model.common.enums.RolArbitral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TernaDetalleRepository extends JpaRepository<TernaDetalle, Long> {

    // Ya existente
    List<TernaDetalle> findByTerna_Id(Long ternaId);

    // Unicidad (terna, árbitro, rol)
    boolean existsByTerna_IdAndArbitro_IdAndRol(Long ternaId, Long arbitroId, RolArbitral rol);
}
