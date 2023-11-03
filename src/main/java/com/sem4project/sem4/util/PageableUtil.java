package com.sem4project.sem4.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableUtil {
    public static Pageable calculatePageable(long count, int pageNumber, int pageSize, String sortBy) {
        int maxPage = (int) Math.ceil((double) count / pageSize) - 1;
        Sort sort = Sort.by(Objects.requireNonNullElse(sortBy, "updatedAt"));
        pageNumber--;
        pageNumber = Math.max(0, pageNumber);
        pageNumber = Math.min(pageNumber, maxPage);
        return PageRequest.of(Math.max(0, pageNumber), pageSize, sort);
    }
}
