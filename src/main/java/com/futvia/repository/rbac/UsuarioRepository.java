// src/main/java/com/futvia/repository/rbac/UsuarioRepository.java
package com.futvia.repository.rbac;

import com.futvia.model.rbac.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Unicidad / login
    Optional<Usuario> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);

    // Filtros frecuentes
    Page<Usuario> findByEstado(boolean estado, Pageable pageable);                 // activo/inactivo (campo estado) :contentReference[oaicite:5]{index=5}
    Page<Usuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
            String nombre, String apellido, Pageable pageable);

    // Búsquedas masivas (para importaciones/operaciones por lotes)
    List<Usuario> findByEmailIn(Collection<String> emails);

    // Mantenimiento
    long deleteByEstadoFalse(); // eliminar inactivos en limpieza controlada
}
