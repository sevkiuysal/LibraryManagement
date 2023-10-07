package com.koylumuhendis.librarymanagement.service;

import com.koylumuhendis.librarymanagement.dto.BookListItemResponse;
import com.koylumuhendis.librarymanagement.dto.BookSaveRequest;
import com.koylumuhendis.librarymanagement.dto.CategoryType;
import com.koylumuhendis.librarymanagement.model.Book;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import com.koylumuhendis.librarymanagement.model.Category;
import com.koylumuhendis.librarymanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookSaveServiceTest extends BaseServiceTest {

    @InjectMocks
    private BookSaveService bookSaveService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setup(){
        bookRepository=mock(BookRepository.class);
        categoryService=mock(CategoryService.class);
        bookSaveService=new BookSaveService(bookRepository,categoryService);
    }

    @Test
    void itShouldReturnBookListItemResponse_WhenNewSaveBook() {
        // given

        Category category = new Category();
        category.setName(CategoryType.COMIC.getValue());


        BookSaveRequest request = new BookSaveRequest.builder()
                .bookStatus(BookStatus.READING)
                .title("title")
                .categoryId(1L)
                .lastPageNumber(1)
                .totalPage(1)
                .build();

        Book book = new Book.builder().category(category)
                .bookStatus(request.getBookStatus())
                .title(request.getTitle())
                .publisher(request.getPublisher())
                .lastPageNumber(request.getLastPageNumber())
                .authorName(request.getAuthorName())
                .totalPage(request.getTotalPage())
                .id(1L)
                .build();
        Book book1=new Book.builder().build();
        // when
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(categoryService.loadCategory(anyLong())).thenReturn(category);

        // then
        BookListItemResponse expected=new BookListItemResponse.builder()
                .id(book.getId())
                .bookStatus(book.getBookStatus())
                .authorName(book.getAuthorName())
                .publisher(book.getPublisher())
                .title(book.getTitle())
                .totalPage(book.getTotalPage())
                .lastPageNumber(book.getLastPageNumber())
                .categoryName(book.getCategory().getName())
                .build();
        BookListItemResponse actual = bookSaveService.saveBook(request);

        assertEquals(actual.getTitle(),expected.getTitle());

    }
}