package com.futvia.repository.club;

import com.futvia.model.club.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CuerpoTecnicoRepository extends JpaRepository<CuerpoTecnico, Long> {
    List<CuerpoTecnico> findByEquipo_Id(Long equipoId);
}