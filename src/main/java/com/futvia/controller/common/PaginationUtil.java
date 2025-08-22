package com.futvia.controller.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

/**
 * Utility to translate Spring Page<?> to PageResponse<?>.
 */
public final class PaginationUtil {

    private PaginationUtil() {}

    public static <T> PageResponse<T> from(Page<T> page) {
        String sort = sortToString(page.getSort());
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.getNumberOfElements(),
                page.isEmpty(),
                sort
        );
    }

    private static String sortToString(Sort s) {
        if (s == null || s.isUnsorted()) return "unsorted";
        return s.stream()
                .map(o -> o.getProperty() + " " + o.getDirection())
                .collect(Collectors.joining(", "));
    }
}