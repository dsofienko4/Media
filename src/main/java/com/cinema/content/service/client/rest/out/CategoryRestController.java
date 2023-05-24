package com.cinema.content.service.client.rest.out;

import com.cinema.content.service.back.domain.Category;
import com.cinema.content.service.back.dto.response.CategoriesResponseDto;
import com.cinema.content.service.client.facade.CategoryServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryServiceFacade categoryServiceFacade;

    @GetMapping("/categories")
    public ResponseEntity <List<CategoriesResponseDto>> listDto () {
        List<CategoriesResponseDto> dtoList = categoryServiceFacade.getListDto();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/categories")
    public ResponseEntity<Void> addCategory (@RequestParam String name) {
        categoryServiceFacade.addCategory(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Void> updateCategory (@RequestParam String name, @PathVariable Long id) {
        categoryServiceFacade.updateCategory(name,id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory (@PathVariable Long id) {
        categoryServiceFacade.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
