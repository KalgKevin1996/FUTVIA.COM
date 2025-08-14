package com.futvia.repository.geo;

import com.futvia.model.geo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
