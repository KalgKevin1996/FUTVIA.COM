// src/main/java/com/futvia/repository/club/CuerpoTecnicoRepository.java
package com.futvia.repository.club;

import com.futvia.model.club.CuerpoTecnico;
import com.futvia.model.common.enums.RolTecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuerpoTecnicoRepository extends JpaRepository<CuerpoTecnico, Long> {
    List<CuerpoTecnico> findByEquipo_Id(Long equipoId); // existente
    List<CuerpoTecnico> findByEquipo_IdAndRolTecnico(Long equipoId, RolTecnico rolTecnico); // :contentReference[oaicite:14]{index=14}
    List<CuerpoTecnico> findByUsuario_Id(Long usuarioId); // si el DT/AT está vinculado a Usuario :contentReference[oaicite:15]{index=15}
    long deleteByEquipo_Id(Long equipoId); // utilitario para reseteos
}
