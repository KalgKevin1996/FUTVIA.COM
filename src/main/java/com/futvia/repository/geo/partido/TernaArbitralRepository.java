package com.futvia.repository.geo.partido;

import com.futvia.model.partido.*;
import com.futvia.model.common.enums.EstadoPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.*;
import java.util.*;

public interface TernaArbitralRepository extends JpaRepository<TernaArbitral, Long> {
    Optional<TernaArbitral> findByPartido_Id(Long partidoId);
}