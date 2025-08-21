// src/main/java/com/futvia/repository/competicion/CompeticionRepository.java
package com.futvia.repository.competicion;

import com.futvia.model.competicion.Competicion;
import com.futvia.model.common.enums.TipoCompeticion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {

    // existentes en tu doc (añado variantes paginadas)
    List<Competicion> findByTipo(TipoCompeticion tipo);
    List<Competicion> findByNombreContainingIgnoreCase(String q);

    Page<Competicion> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    Page<Competicion> findByTipo(TipoCompeticion tipo, Pageable pageable);

    // por organizador
    List<Competicion> findByOrganizador_Id(Long organizadorId);
    Page<Competicion> findByOrganizador_Id(Long organizadorId, Pageable pageable);

    // por alcance geográfico (opcionales)
    Page<Competicion> findByPais_Id(Long paisId, Pageable pageable);
    Page<Competicion> findByDepartamento_Id(Long departamentoId, Pageable pageable);
    Page<Competicion> findByMunicipio_Id(Long municipioId, Pageable pageable);
    Page<Competicion> findByZona_Id(Long zonaId, Pageable pageable);

    // ayuda para evitar duplicados de nombre bajo el mismo organizador
    boolean existsByOrganizador_IdAndNombreIgnoreCase(Long organizadorId, String nombre);
}
