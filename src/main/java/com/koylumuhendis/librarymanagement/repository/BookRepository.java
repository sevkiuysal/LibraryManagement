package com.koylumuhendis.librarymanagement.repository;


import com.koylumuhendis.librarymanagement.model.Book;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookStatus(BookStatus bookStatus);
    List<Book> findByTitle(String title);
}
