package com.koylumuhendis.librarymanagement.repository;

import com.koylumuhendis.librarymanagement.model.Book;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void itShouldFindBookbyBookStatus_WhenBookExist(){
        BookStatus status=BookStatus.READ;

        Book book=new Book.builder().bookStatus(status).build();
        Book book2=new Book.builder().bookStatus(BookStatus.READING).build();

        bookRepository.save(book);
        bookRepository.save(book2);
        List<Book> expected=new ArrayList<>();
        expected.add(book);

        List<Book> actual = bookRepository.findByBookStatus(status);

        assertEquals(actual,expected);
    }

    @Test
    void itShouldFindBookbyTitle_WhenBookExist(){
        String title="title";

        Book book=new Book.builder().title(title).build();

        bookRepository.save(book);

        List<Book> expected=new ArrayList<>();
        expected.add(book);

        List<Book> actual = bookRepository.findByTitle(title);

        assertEquals(actual,expected);
    }

}