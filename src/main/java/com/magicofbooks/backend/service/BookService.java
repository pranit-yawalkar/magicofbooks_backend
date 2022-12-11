package com.magicofbooks.backend.service;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long bookId);
    Book createBook(BookDTO bookDTO, MultipartFile file) throws IOException;
    Book updateBook(Long bookId, BookDTO bookDTO);
    void deleteBook(Long bookId);
}
