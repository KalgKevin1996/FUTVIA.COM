// src/main/java/com/futvia/service/rbac/UsuarioServiceImpl.java
package com.futvia.service.rbac;

import com.futvia.dto.rbac.UsuarioDTO;
import com.futvia.dto.rbac.UsuarioFormDTO;
import com.futvia.mapper.rbac.UsuarioMapper;
import com.futvia.model.rbac.Usuario;
import com.futvia.repository.rbac.UsuarioRepository;
import com.futvia.service.common.BusinessException;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;
    private final UsuarioMapper mapper;

    @Override
    public Page<UsuarioDTO> listar(String q, Boolean estado, Pageable pageable) {
        if (estado != null) {
            return repo.findByEstado(estado, pageable).map(mapper::toDto);
        }
        if (q != null && !q.isBlank()) {
            return repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(q, q, pageable)
                    .map(mapper::toDto);
        }
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return repo.findByEmailIgnoreCase(email).map(mapper::toDto);
    }

    @Override
    public UsuarioDTO obtener(Long id) {
        Usuario e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + id));
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public UsuarioDTO crear(UsuarioFormDTO form) {
        if (repo.existsByEmailIgnoreCase(form.getEmail())) {
            throw new BusinessException("Email ya registrado.");
        }
        Usuario e = mapper.toEntity(form);
        return mapper.toDto(repo.save(e));
    }

    @Override
    @Transactional
    public UsuarioDTO editar(Long id, UsuarioFormDTO form) {
        Usuario e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + id));

        if (!e.getEmail().equalsIgnoreCase(form.getEmail())
                && repo.existsByEmailIgnoreCase(form.getEmail())) {
            throw new BusinessException("Email ya registrado.");
        }

        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
