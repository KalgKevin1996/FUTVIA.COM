package com.futvia.repository.geo;

import com.futvia.model.geo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    List<Municipio> findByDepartamento_Id(Long departamentoId);
}