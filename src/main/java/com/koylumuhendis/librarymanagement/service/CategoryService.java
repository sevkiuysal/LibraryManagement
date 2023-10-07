package com.koylumuhendis.librarymanagement.service;

import com.koylumuhendis.librarymanagement.model.Category;
import com.koylumuhendis.librarymanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category loadCategory(Long id){

        return (Category) categoryRepository.findById(id).orElseThrow();
    }

    public Category findByName(String type){
        return categoryRepository.findByName(type).orElseThrow(RuntimeException::new);
    }
}
