package com.futvia.repository.competicion;

import com.futvia.model.competicion.*;
import com.futvia.model.common.enums.TipoCompeticion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {
    List<Competicion> findByTipo(TipoCompeticion tipo);
    List<Competicion> findByNombreContainingIgnoreCase(String q);
}