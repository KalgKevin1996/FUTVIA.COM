package com.futvia.service.rbac;

import com.futvia.dto.rbac.AsignacionRolDTO;
import com.futvia.dto.rbac.AsignacionRolFormDTO;
import com.futvia.mapper.rbac.AsignacionRolMapper;
import com.futvia.model.rbac.AsignacionRol;
import com.futvia.repository.rbac.AsignacionRolRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsignacionRolServiceImpl implements AsignacionRolService {

    private final AsignacionRolRepository repo;
    private final AsignacionRolMapper mapper;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Page<AsignacionRolDTO> listarPorUsuario(Long usuarioId, Pageable pageable){
        return repo.findByUsuario_Id(usuarioId, pageable)
                .map(mapper::toDto); // ← sin null extra
    }

    @Transactional
    public AsignacionRolDTO asignar(AsignacionRolFormDTO form){
        AsignacionRol e = mapper.toEntity(form);   // ← sin null extra
        AsignacionRol saved = repo.save(e);
        return mapper.toDto(saved);                // ← sin null extra
    }

    @Transactional
    public void revocar(Long id){
        if (!repo.existsById(id)) {
            throw new NotFoundException("Asignación no existe: " + id);
        }
        repo.deleteById(id);
    }
}
