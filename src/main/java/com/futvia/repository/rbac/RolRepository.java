package com.futvia.repository.rbac;

import com.futvia.model.rbac.Rol;
import com.futvia.model.common.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(RolNombre nombre);
}