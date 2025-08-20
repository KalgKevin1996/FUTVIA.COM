// src/main/java/com/futvia/service/common/PageResponseFactory.java
package com.futvia.service.common;

import com.futvia.dto.common.PageResponseDTO;
import org.springframework.data.domain.Page;

public class PageResponseFactory {
    public static <T> PageResponseDTO<T> from(Page<T> page) {
        return PageResponseDTO.<T>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}
