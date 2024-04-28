package com.emsi.salesmasterbe2.utils;

import com.emsi.salesmasterbe2.payload.response.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ApiServiceUtils {
    public <T, U> PagedResponse<U> getAllEntities(
            int page,
            int size,
            JpaRepository<T, Long> repository,
            Class<U> dtoClass) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<T> entitiesPage = repository.findAll(pageable);
        long totalElementsInTable = repository.count();
        return new PagedResponse<>(
                ObjectMapperUtils.mapAll(entitiesPage.getContent(), dtoClass),
                page,
                size,
                entitiesPage.getNumberOfElements(),
                entitiesPage.getTotalPages(),
                totalElementsInTable);
    }
}
