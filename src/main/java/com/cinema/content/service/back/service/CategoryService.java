package com.cinema.content.service.back.service;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.dto.response.CategoriesResponseDto;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);

    List<CategoriesResponseDto> getListDto();

    void addCategory (String name);

    void updateCategory(String name, Long id);

    void deleteCategory(Long id);
}
