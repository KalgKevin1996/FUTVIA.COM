package com.futvia.service.geo;

import com.futvia.dto.geo.*;
import org.springframework.data.domain.*;

public interface PaisService { Page<PaisDTO> listar(Pageable p); PaisDTO crear(PaisFormDTO f); }