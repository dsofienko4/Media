package com.cinema.content.service.back.service.impl;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.dto.response.CategoriesResponseDto;
import com.cinema.content.service.back.exception.NotFoundEntityExceptional;
import com.cinema.content.service.back.repository.CategoryRepository;
import com.cinema.content.service.back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundEntityExceptional("There is no such category");
        }
        return category.get();
    }

    @Override
    public List<CategoriesResponseDto> getListDto() {
        List<Category> listCategory = categoryRepository.findAll();
        List<CategoriesResponseDto> dtoList = listCategory.stream().map(new Function<Category, CategoriesResponseDto>() {
            @Override
            public CategoriesResponseDto apply(Category category) {
                return new CategoriesResponseDto(category.getId(), category.getName());
            }
        }).toList();
        return dtoList;
    }

    @Override
    public void addCategory(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);

    }

    @Override
    public void updateCategory(String name, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new NotFoundEntityExceptional("There is no such category");
        } else {
            Category category = categoryOptional.get();
            category.setName(name);
            categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundEntityExceptional("There is no such category");
        }
        categoryRepository.deleteById(id);
    }

}
