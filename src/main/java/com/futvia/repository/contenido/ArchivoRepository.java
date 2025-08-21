// src/main/java/com/futvia/repository/contenido/ArchivoRepository.java
package com.futvia.repository.contenido;

import com.futvia.model.contenido.Archivo;
import com.futvia.model.common.enums.ArchivoTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    // --- EXISTENTES / ÚTILES ---
    List<Archivo> findByTipo(ArchivoTipo tipo);
    boolean existsByS3Key(String s3Key);
    Optional<Archivo> findByS3Key(String s3Key);
    List<Archivo> findByCreadoPorIgnoreCase(String creadoPor);

    // --- NUEVOS: versión paginada/eficiente para usar desde Service ---
    Page<Archivo> findByTipo(ArchivoTipo tipo, Pageable pageable);
    Page<Archivo> findByCreadoPorIgnoreCase(String creadoPor, Pageable pageable);
}
