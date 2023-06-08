package com.irb.plantas.useCase;

import com.irb.plantas.model.Category;
import com.irb.plantas.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryUseCase {
    final CategoryRepository categoryRepository;

    public List<Category> getCategories() {

        return this.categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID());
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(UUID idCategory) {
        this.categoryRepository.deleteById(idCategory);
    }

}
