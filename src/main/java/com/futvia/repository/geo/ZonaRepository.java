// src/main/java/com/futvia/repository/geo/ZonaRepository.java
package com.futvia.repository.geo;

import com.futvia.model.geo.Zona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    // Unicidad por municipio (número de zona único dentro del municipio)
    Optional<Zona> findByMunicipio_IdAndNumero(Long municipioId, Integer numero);
    boolean existsByMunicipio_IdAndNumero(Long municipioId, Integer numero);

    // Listados y búsqueda
    Page<Zona> findByMunicipio_Id(Long municipioId, Pageable pageable);
    List<Zona> findByMunicipio_Id(Long municipioId);
    Page<Zona> findByNumero(Integer numero, Pageable pageable);
    Page<Zona> findByMunicipio_IdAndNumero(Long municipioId, Integer numero, Pageable pageable);

    // Mantenimiento
    long deleteByMunicipio_Id(Long municipioId);
    long countByMunicipio_Id(Long municipioId);
}
