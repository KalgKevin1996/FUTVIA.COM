package com.futvia.repository.partido;

import com.futvia.model.partido.*;
import com.futvia.model.common.enums.EstadoPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.*;
import java.util.*;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
    List<Jornada> findByTemporada_Id(Long temporadaId);
}