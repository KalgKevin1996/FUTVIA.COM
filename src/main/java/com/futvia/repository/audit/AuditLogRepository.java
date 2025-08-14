package com.futvia.repository.audit;

import com.futvia.model.audit.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}