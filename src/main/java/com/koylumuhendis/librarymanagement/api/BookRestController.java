package com.koylumuhendis.librarymanagement.api;

import com.koylumuhendis.librarymanagement.dto.BookListItemResponse;
import com.koylumuhendis.librarymanagement.dto.BookResponse;
import com.koylumuhendis.librarymanagement.dto.BookSaveRequest;
import com.koylumuhendis.librarymanagement.dto.CategoryType;
import com.koylumuhendis.librarymanagement.model.BookStatus;
import com.koylumuhendis.librarymanagement.service.BookListService;
import com.koylumuhendis.librarymanagement.service.BookSaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookRestController {

    private final BookSaveService bookSaveService;
    private final BookListService bookListService;

    public BookRestController(BookSaveService bookSaveService,BookListService bookListService) {
    	this.bookListService=bookListService;
    	this.bookSaveService=bookSaveService;
    }

    @PostMapping(name="/save")
    public ResponseEntity<BookListItemResponse> saveBook(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookSaveService.saveBook(bookSaveRequest));
    }
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> listBook(
            int size,
            int page){
        return ResponseEntity
                .ok()
                .body(bookListService.listBooks(page,size));
    }
    @GetMapping("/list/{categoryType}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable CategoryType categoryType){
        return ResponseEntity.ok().body(bookListService.searchByCategory(categoryType));
    }
    @GetMapping("/list/{bookStatus}")
    public ResponseEntity<List<BookResponse>> listByStatus(@PathVariable BookStatus bookStatus){
        return ResponseEntity.ok().body(bookListService.searchBookStatus(bookStatus));
    }
    @GetMapping("/list/{title}")
    public ResponseEntity<List<BookResponse>> listByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(bookListService.searchBookByTittle(title));
    }
}
