package com.futvia.repository.club;

import com.futvia.model.club.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByCompeticion_Id(Long competicionId);
    List<Equipo> findByCategoria_Id(Long categoriaId);
}