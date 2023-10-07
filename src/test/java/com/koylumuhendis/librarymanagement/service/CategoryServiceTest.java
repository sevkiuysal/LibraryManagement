package com.koylumuhendis.librarymanagement.service;

import com.koylumuhendis.librarymanagement.model.Category;
import com.koylumuhendis.librarymanagement.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest extends BaseServiceTest{

    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    void setup(){
        categoryRepository= Mockito.mock(CategoryRepository.class);
        categoryService= new CategoryService(categoryRepository);
    }

    @Test
    void itShouldLoadCategorybyId_WhenCategoryExist(){
        Long id= 1L;
        Category expected=new Category();
        expected.setName("name");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(expected));
        Category actual = categoryService.loadCategory(id);
        assertEquals(actual,expected);
    }

    @Test
    void itShouldFindCategorybyName_WhenCategoryExist(){
        String name="name";
        Category expected=new Category();
        expected.setName(name);

        when(categoryRepository.findByName(name)).thenReturn(Optional.of(expected));
        Category actual = categoryService.findByName(name);
        assertEquals(actual,expected);
    }
    @Test
    void itShouldLoadCategorybyId_WhenCategoryNotExist_thenExceptionIsThrown(){

        when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,() -> categoryService.loadCategory(Mockito.anyLong()));
    }

    @Test
    void itShouldFindCategorybyName_WhenCategoryNotExist_thenExceptionIsThrown(){
        when(categoryRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> categoryService.findByName(Mockito.anyString()));
    }

}