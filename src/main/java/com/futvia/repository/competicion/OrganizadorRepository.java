package com.futvia.repository.competicion;

import com.futvia.model.competicion.*;
import com.futvia.model.common.enums.TipoCompeticion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {}
