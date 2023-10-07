package com.koylumuhendis.librarymanagement.service;


import com.koylumuhendis.librarymanagement.dto.BookResponse;
import com.koylumuhendis.librarymanagement.dto.CategoryType;
import com.koylumuhendis.librarymanagement.model.Book;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import com.koylumuhendis.librarymanagement.model.Category;
import com.koylumuhendis.librarymanagement.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookListService {
	private final BookRepository bookRepository;
	private final CategoryService categoryService;

	public BookListService(BookRepository bookRepository, CategoryService categoryService) {
		this.bookRepository = bookRepository;
		this.categoryService = categoryService;
	}

	public List<BookResponse> listBooks(int page, int size) {

		return bookRepository
				.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this::convertResponse)// BookListService::convertResponse==
				// kullanmak istenirse convertResponse methodu static yapılmalıdır
				.collect(Collectors.toList());
	}

	public List<BookResponse> searchByCategory(CategoryType categoryType) {
		Category category = categoryService
				.findByName(categoryType.getValue());
		return category.getBookList()
				.stream()
				.map(this::convertResponse)// Lambda expression
				.collect(Collectors.toList());
	}

	public List<BookResponse> searchBookStatus(BookStatus bookStatus) {
		return bookRepository
				.findByBookStatus(bookStatus)
				.stream()
				.map(model -> 
				new BookResponse.builder()
				.id(model.getId())
				.imageUrl(model.getImage().getImageURL())
				.build())
				.collect(Collectors.toList());
	}

	public List<BookResponse> searchBookByTittle(String title) {
		return bookRepository
				.findByTitle(title)
				.stream()
				.map(model -> 
				new BookResponse.builder()
				.id(model.getId())
				.imageUrl(model.getImage().getImageURL())
				.build())
				.collect(Collectors.toList());
	}

	private BookResponse convertResponse(Book book) {
		return new BookResponse.builder()
				.authorName(book.getAuthorName())
				.title(book.getTitle())
				.imageUrl(book.getImage().getImageURL())
				.build();
	}
}
