package com.futvia.repository.audit;

import com.futvia.model.audit.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportLogRepository extends JpaRepository<ExportLog, Long> {

}
