package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.RegisterDTO;
import com.magicofbooks.backend.dto.ResponseDTO;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = this.bookService.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Book> createBook(@RequestPart("book") BookDTO bookDTO,
                                           @RequestPart("imageFile") MultipartFile file) {
        try {
            Book book1 = this.bookService.createBook(bookDTO, file);
            return new ResponseEntity<>(book1, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        Book updatedBook = this.bookService.updateBook(bookId, bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable Long bookId) {
        this.bookService.deleteBook(bookId);
        ResponseDTO responseDTO = new ResponseDTO(true, "Book deleted successfully!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
