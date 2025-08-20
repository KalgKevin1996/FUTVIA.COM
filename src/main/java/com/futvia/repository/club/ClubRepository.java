package com.futvia.repository.club;

import com.futvia.model.club.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Page<Club> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
