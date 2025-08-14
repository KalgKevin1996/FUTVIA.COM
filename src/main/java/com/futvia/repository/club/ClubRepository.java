package com.futvia.repository.club;

import com.futvia.model.club.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByNombreContainingIgnoreCase(String q);
}