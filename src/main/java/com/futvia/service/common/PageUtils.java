package com.futvia.service.common;


import org.springframework.data.domain.*;


public final class PageUtils {
    private PageUtils() {}
    public static Pageable of(int page, int size, String sort) {
        return PageRequest.of(page, size, Sort.by(sort == null? "id": sort).ascending());
    }
}