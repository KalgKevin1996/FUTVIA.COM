package com.futvia.repository.club;

import com.futvia.model.club.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByNombreContainingIgnoreCase(String q);
    Page<Club> findByNombreContainingIgnoreCase(String q, Pageable pageable);
    boolean existsByNombreIgnoreCase(String nombre);
    // sede opcional: Club.municipio
    List<Club> findByMunicipio_Id(Long municipioId); // :contentReference[oaicite:3]{index=3}
}