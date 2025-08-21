package com.futvia.service.disciplina;

import com.futvia.dto.disciplina.ReporteDisciplinarioDTO;
import com.futvia.dto.disciplina.ReporteDisciplinarioFormDTO;
import com.futvia.mapper.RefMapper;
import com.futvia.mapper.disciplina.ReporteDisciplinarioMapper;
import com.futvia.model.disciplina.ReporteDisciplinario;
import com.futvia.repository.disciplina.ReporteDisciplinarioRepository;
import com.futvia.service.common.NotFoundException;
import com.futvia.service.disciplina.ReporteDisciplinarioService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReporteDisciplinarioServiceImpl implements ReporteDisciplinarioService {

    private final ReporteDisciplinarioRepository repo;
    private final ReporteDisciplinarioMapper mapper;
    private final RefMapper ref;

    public ReporteDisciplinarioServiceImpl(ReporteDisciplinarioRepository repo,
                                           ReporteDisciplinarioMapper mapper,
                                           RefMapper ref) {
        this.repo = repo;
        this.mapper = mapper;
        this.ref = ref;
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteDisciplinarioDTO obtener(Long id) {
        ReporteDisciplinario e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Reporte disciplinario no encontrado"));
        return mapper.toDto(e, ref); // usa partidoId/reportanteId y derivados para UI. :contentReference[oaicite:4]{index=4}
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReporteDisciplinarioDTO> listarPorPartido(Long partidoId, Pageable pageable) {
        return repo.findByPartido_Id(partidoId, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReporteDisciplinarioDTO> listarPorTemporada(Long temporadaId, Pageable pageable) {
        return repo.findByTemporada(temporadaId, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReporteDisciplinarioDTO> listarPorReportante(Long usuarioId, Pageable pageable) {
        return repo.findByReportante_Id(usuarioId, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReporteDisciplinarioDTO> buscar(String q, Pageable pageable) {
        return repo.search(q, pageable).map(e -> mapper.toDto(e, ref));
    }

    @Override
    public ReporteDisciplinarioDTO crear(ReporteDisciplinarioFormDTO form) {
        // MapStruct: setea partido y reportante por id desde el FormDTO. :contentReference[oaicite:5]{index=5}
        ReporteDisciplinario e = mapper.toEntity(form);
        e = repo.save(e);
        return mapper.toDto(e, ref);
    }

    @Override
    public ReporteDisciplinarioDTO actualizar(Long id, ReporteDisciplinarioFormDTO form) {
        ReporteDisciplinario actual = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Reporte disciplinario no encontrado"));
        // Como el mapper no trae un update(), reasignamos «a la segura»
        ReporteDisciplinario mod = mapper.toEntity(form);
        actual.setPartido(mod.getPartido());
        actual.setReportante(mod.getReportante());
        actual.setDescripcion(form.getDescripcion());
        return mapper.toDto(actual, ref);
    }

    @Override
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Reporte disciplinario no encontrado");
        repo.deleteById(id);
    }
}
