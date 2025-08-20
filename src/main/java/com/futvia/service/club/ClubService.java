package com.futvia.service.club;

import com.futvia.dto.club.ClubDTO;
import com.futvia.dto.club.ClubFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubService {
    Page<ClubDTO> listar(Pageable pageable);
    Page<ClubDTO> buscar(String q, Pageable pageable);
    ClubDTO crear(ClubFormDTO form);
    ClubDTO editar(Long id, ClubFormDTO form);
    void eliminar(Long id);
}
