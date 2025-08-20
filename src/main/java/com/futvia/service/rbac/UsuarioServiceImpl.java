package com.futvia.service.rbac;


import com.futvia.dto.rbac.*;
import com.futvia.mapper.rbac.UsuarioMapper;
import com.futvia.model.rbac.Usuario;
import com.futvia.repository.rbac.UsuarioRepository;
import com.futvia.service.common.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service @RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repo;
    private final UsuarioMapper mapper;


    @Override
    public Page<UsuarioDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }


    @Override
    public Page<UsuarioDTO> buscar(String q, Pageable pageable) {
        if(q == null || q.isBlank()) return listar(pageable);
// Si no tienes método específico, usa findAll y filtra por email/nombre con Example o Specification.
        return repo.findAll(pageable).map(mapper::toDto);
    }


    @Override
    public UsuarioDTO buscarPorId(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }


    @Override
    public UsuarioDTO buscarPorEmail(String email) {
        return repo.findByEmailIgnoreCase(email).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Usuario con email no existe"));
    }


    @Override @Transactional
    public UsuarioDTO crear(UsuarioFormDTO form) {
        if(repo.existsByEmailIgnoreCase(form.getEmail()))
            throw new IllegalArgumentException("Email ya registrado");
        Usuario entity = mapper.toEntity(form);
        return mapper.toDto(repo.save(entity));
    }


    @Override @Transactional
    public UsuarioDTO editar(Long id, UsuarioFormDTO form) {
        Usuario e = repo.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        mapper.update(e, form);
        return mapper.toDto(repo.save(e));
    }


    @Override @Transactional
    public void eliminar(Long id) { repo.deleteById(id); }


    @Override @Transactional
    public void activar(Long id, boolean estado) {
        Usuario e = repo.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        e.setEstado(estado);
        repo.save(e);
    }
}