// src/main/java/com/futvia/repository/partido/EstadioRepository.java
package com.futvia.repository.partido;

import com.futvia.model.partido.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {

    // Filtros útiles para selector de sedes
    List<Estadio> findByMunicipio_Id(Long municipioId);

    boolean existsByNombreIgnoreCaseAndMunicipio_Id(String nombre, Long municipioId);
}
