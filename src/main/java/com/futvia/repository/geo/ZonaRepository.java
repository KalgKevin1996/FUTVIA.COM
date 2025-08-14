package com.futvia.repository.geo;

import com.futvia.model.geo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    List<Zona> findByMunicipio_Id(Long municipioId);
}