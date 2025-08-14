package com.futvia.repository.geo;

import com.futvia.model.geo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    List<Departamento> findByPais_Id(Long paisId);
}