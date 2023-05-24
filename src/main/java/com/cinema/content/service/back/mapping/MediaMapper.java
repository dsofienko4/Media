package com.cinema.content.service.back.mapping;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.dto.response.CategoriesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {
    public CategoriesResponseDto convertToDto(Category category) {
        return new CategoriesResponseDto(category.getId(), category.getName());
    }
}
