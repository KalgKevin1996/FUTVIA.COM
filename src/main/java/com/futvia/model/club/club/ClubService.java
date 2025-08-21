// src/main/java/com/futvia/service/club/ClubService.java
package com.futvia.model.club.club;

import com.futvia.dto.club.ClubDTO;
import com.futvia.dto.club.ClubFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubService {
    Page<ClubDTO> listar(String q, Pageable pageable);
    ClubDTO obtener(Long id);
    ClubDTO crear(ClubFormDTO form);
    ClubDTO editar(Long id, ClubFormDTO form);
    void eliminar(Long id);
}
