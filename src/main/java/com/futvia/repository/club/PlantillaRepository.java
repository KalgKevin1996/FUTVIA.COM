package com.futvia.repository.club;

import com.futvia.model.club.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {
    List<Plantilla> findByEquipo_IdAndTemporada_Id(Long equipoId, Long temporadaId);
}