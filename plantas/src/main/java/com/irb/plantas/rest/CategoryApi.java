package com.irb.plantas.rest;

import com.irb.plantas.model.Category;
import com.irb.plantas.model.User;
import com.irb.plantas.useCase.CategoryUseCase;
import com.irb.plantas.useCase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryUseCase categoryUseCase;

    @PostMapping
    public ResponseEntity<Category> createCategory(@Validated @RequestBody Category category) {
        return new ResponseEntity<>(categoryUseCase.createCategory(category), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryUseCase.getCategories(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam UUID idCategory) {
        categoryUseCase.deleteCategory(idCategory);
    }

}

