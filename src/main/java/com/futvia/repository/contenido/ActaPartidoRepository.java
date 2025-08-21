// src/main/java/com/futvia/repository/contenido/ActaPartidoRepository.java
package com.futvia.repository.contenido;

import com.futvia.model.contenido.ActaPartido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActaPartidoRepository extends JpaRepository<ActaPartido, Long> {

    // Recuperar acta por partido (un partido debería tener solo un acta)
    Optional<ActaPartido> findByPartido_Id(Long partidoId);

    // Listar actas relacionadas a un archivo específico
    List<ActaPartido> findByArchivo_Id(Long archivoId);
}
