package com.cinema.content.service.client.facade.impl;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.dto.response.CategoriesResponseDto;
import com.cinema.content.service.back.service.CategoryService;
import com.cinema.content.service.client.facade.CategoryServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryServiceFacadeImpl implements CategoryServiceFacade {

    private final CategoryService categoryService;

    @Override
    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public List<CategoriesResponseDto> getListDto() {
        return categoryService.getListDto();
    }

    @Override
    public void addCategory(String name) {
        categoryService.addCategory(name);
    }

    @Override
    public void updateCategory(String name, Long id) {
        categoryService.updateCategory(name,id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }

}
