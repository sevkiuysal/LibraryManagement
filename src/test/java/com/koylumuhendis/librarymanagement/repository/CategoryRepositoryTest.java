package com.koylumuhendis.librarymanagement.repository;

import com.koylumuhendis.librarymanagement.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void itShouldFindCategorybyCategoryName_WhenCategoryExist() {
        String categoryName = "categoryName";

        Category expected = new Category();
        expected.setName(categoryName);

        categoryRepository.save(expected);

        Category actual = categoryRepository.findByName(categoryName).get();

        assertEquals(actual, expected);
    }
}