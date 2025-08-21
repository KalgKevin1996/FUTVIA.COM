// src/main/java/com/futvia/service/contenido/ArchivoServiceImpl.java
package com.futvia.service.contenido;

import com.futvia.dto.contenido.ArchivoDTO;
import com.futvia.dto.contenido.ArchivoFormDTO;
import com.futvia.mapper.contenido.ArchivoMapper;
import com.futvia.model.common.enums.ArchivoTipo;
import com.futvia.model.contenido.Archivo;
import com.futvia.repository.contenido.ArchivoRepository;
import com.futvia.service.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArchivoServiceImpl implements ArchivoService {

    private final ArchivoRepository repo;
    private final ArchivoMapper mapper;

    @Override
    public Page<ArchivoDTO> listar(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public Page<ArchivoDTO> listarPorTipo(ArchivoTipo tipo, Pageable pageable) {
        if (tipo == null) {
            // Fallback sensato: si no envían tipo, listamos todo (evita NPE).
            return listar(pageable);
        }
        return repo.findByTipo(tipo, pageable).map(mapper::toDto);
    }

    @Override
    public Page<ArchivoDTO> buscarPorCreador(String creadoPor, Pageable pageable) {
        if (creadoPor == null || creadoPor.isBlank()) {
            return Page.empty(pageable == null ? PageRequest.of(0, 20) : pageable);
        }
        return repo.findByCreadoPorIgnoreCase(creadoPor, pageable).map(mapper::toDto);
    }

    @Override
    public ArchivoDTO obtener(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Archivo no encontrado"));
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public ArchivoDTO crear(ArchivoFormDTO form) {
        validarFormCrear(form);
        if (repo.existsByS3Key(form.getS3Key())) {
            throw new IllegalStateException("Ya existe un archivo con esa s3Key");
        }
        Archivo e = mapper.toEntity(form);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public ArchivoDTO editar(Long id, ArchivoFormDTO form) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Archivo no encontrado"));

        // si cambia s3Key, revalidar unicidad
        if (form.getS3Key() != null && !form.getS3Key().isBlank()
                && !form.getS3Key().equals(e.getS3Key())
                && repo.existsByS3Key(form.getS3Key())) {
            throw new IllegalStateException("Ya existe un archivo con esa s3Key");
        }

        mapper.update(e, form);
        e = repo.save(e);
        return mapper.toDto(e);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new NotFoundException("Archivo no encontrado"));
        repo.delete(e);
    }

    // ----------------- helpers -----------------
    private void validarFormCrear(ArchivoFormDTO form) {
        if (form == null) throw new IllegalArgumentException("El formulario es obligatorio");
        if (form.getS3Key() == null || form.getS3Key().isBlank()) {
            throw new IllegalArgumentException("s3Key es obligatorio");
        }
        // Tip: podrías validar tipo/mime/tamaño si tu flujo de subida lo exige
    }
}
