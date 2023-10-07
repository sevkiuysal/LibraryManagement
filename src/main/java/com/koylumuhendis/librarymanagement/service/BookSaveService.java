package com.koylumuhendis.librarymanagement.service;


import com.koylumuhendis.librarymanagement.dto.BookListItemResponse;
import com.koylumuhendis.librarymanagement.dto.BookSaveRequest;
import com.koylumuhendis.librarymanagement.model.Book;
import com.koylumuhendis.librarymanagement.model.Category;
import com.koylumuhendis.librarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookSaveService {
    private  final BookRepository bookRepository;
    private final CategoryService categoryService;


	public BookSaveService(BookRepository bookRepository, CategoryService categoryService) {
		this.bookRepository = bookRepository;
		this.categoryService = categoryService;
	}


	@Transactional
    public BookListItemResponse saveBook(BookSaveRequest bookSaveRequest){
        Category category=categoryService.loadCategory(bookSaveRequest.getCategoryId());
        Book book=new Book.builder()
                .category(category)
                .bookStatus(bookSaveRequest.getBookStatus())
                .title(bookSaveRequest.getTitle())
                .authorName(bookSaveRequest.getAuthorName())
                .lastPageNumber(bookSaveRequest.getLastPageNumber())
                .totalPage(bookSaveRequest.getTotalPage())
                .publisher(bookSaveRequest.getPublisher())
                .build();
        Book fromDb=bookRepository.save(book);
        return new BookListItemResponse.builder()
                .id(fromDb.getId())
                .bookStatus(fromDb.getBookStatus())
                .authorName(fromDb.getAuthorName())
                .publisher(fromDb.getPublisher())
                .title(fromDb.getTitle())
                .totalPage(fromDb.getTotalPage())
                .lastPageNumber(fromDb.getLastPageNumber())
                .categoryName(fromDb.getCategory().getName())
                .build();
    }
}
