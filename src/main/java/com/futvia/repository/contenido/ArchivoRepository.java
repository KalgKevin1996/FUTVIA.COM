// src/main/java/com/futvia/repository/contenido/ArchivoRepository.java
package com.futvia.repository.contenido;

import com.futvia.model.contenido.Archivo;
import com.futvia.model.common.enums.ArchivoTipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    // Buscar por tipo (ej. ESCUDO, FOTO_JUGADOR, REGLAMENTO_PDF)
    List<Archivo> findByTipo(ArchivoTipo tipo);

    // Validar si ya existe un archivo con la misma clave S3
    boolean existsByS3Key(String s3Key);

    // Recuperar archivo por clave S3
    Optional<Archivo> findByS3Key(String s3Key);

    // Buscar archivos creados por un usuario (email/id almacenado en creadoPor)
    List<Archivo> findByCreadoPorIgnoreCase(String creadoPor);
}
