package com.futvia.service.partido;


import com.futvia.dto.partido.*;
import com.futvia.mapper.partido.*;
import com.futvia.model.partido.*;
import com.futvia.repository.partido.*;
import com.futvia.service.common.NotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class EstadioServiceImpl implements EstadioService {

    private final EstadioRepository estadioRepo;
    private final EstadioMapper estadioMapper;

    EstadioServiceImpl(EstadioRepository estadioRepo, EstadioMapper estadioMapper) {
        this.estadioRepo = estadioRepo;
        this.estadioMapper = estadioMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstadioDTO> listar(Pageable pageable) {
        return estadioRepo.findAll(pageable).map(estadioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public EstadioDTO obtener(Long id) {
        Estadio e = estadioRepo.findById(id).orElseThrow(() -> new NotFoundException("Estadio no encontrado"));
        return estadioMapper.toDto(e);
    }

    @Override
    public EstadioDTO crear(EstadioFormDTO form) {
        Estadio e = estadioMapper.toEntity(form);
        e = estadioRepo.save(e);
        return estadioMapper.toDto(e);
    }

    @Override
    public EstadioDTO actualizar(Long id, EstadioFormDTO form) {
        Estadio e = estadioRepo.findById(id).orElseThrow(() -> new NotFoundException("Estadio no encontrado"));
        estadioMapper.update(e, form);
        return estadioMapper.toDto(e);
    }

    @Override
    public void eliminar(Long id) {
        if (!estadioRepo.existsById(id)) throw new NotFoundException("Estadio no encontrado");
        estadioRepo.deleteById(id);
    }
}