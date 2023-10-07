package com.koylumuhendis.librarymanagement.repository;

import com.koylumuhendis.librarymanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> , JpaSpecificationExecutor<Category> {
    public Optional<Category> findByName(String value);
}
