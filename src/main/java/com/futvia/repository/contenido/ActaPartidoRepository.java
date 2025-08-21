// src/main/java/com/futvia/repository/contenido/ActaPartidoRepository.java
package com.futvia.repository.contenido;

import com.futvia.model.contenido.ActaPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActaPartidoRepository extends JpaRepository<ActaPartido, Long> {

    // Un partido debería tener a lo sumo un acta
    Optional<ActaPartido> findByPartido_Id(Long partidoId);

    // Actas asociadas a un archivo (p.ej., para auditoría/limpieza)
    List<ActaPartido> findByArchivo_Id(Long archivoId);
}
