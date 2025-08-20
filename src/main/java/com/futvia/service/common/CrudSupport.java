package com.futvia.service.common;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.function.Function;


public interface CrudSupport<D, F> {
    Page<D> listar(Pageable pageable);
    D buscarPorId(Long id);
    D crear(F form);
    D editar(Long id, F form);
    void eliminar(Long id);
    default <E> Page<D> mapPage(Page<E> page, Function<E, D> mapper){
        return page.map(mapper);
    }
}