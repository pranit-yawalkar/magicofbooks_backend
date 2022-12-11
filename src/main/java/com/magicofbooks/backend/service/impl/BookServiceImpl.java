package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.exception.ResourceNotFoundException;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.model.ImageModel;
import com.magicofbooks.backend.repository.BookRepository;
import com.magicofbooks.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    public Book getBookFromDTO(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookName(bookDTO.getBookName());
        book.setBookDesc(bookDTO.getBookDesc());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());
        book.setBookImage(bookDTO.getBookImage());
        book.setCategory(this.categoryService.getCategoryById(bookDTO.getCategoryId()));
        return book;
    }

    public BookDTO getBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setBookDesc(book.getBookDesc());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setQuantity(book.getQuantity());
        bookDTO.setCategoryId(book.getCategory().getCategoryId());
        bookDTO.setCreatedDate(book.getCreatedDate());
        bookDTO.setModifiedDate(book.getModifiedDate());
        return bookDTO;
    }

    public ImageModel uploadImage(MultipartFile multipartFile) throws IOException {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(multipartFile.getOriginalFilename());
        imageModel.setType(multipartFile.getContentType());
        imageModel.setPicByte(multipartFile.getBytes());
        return imageModel;
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return this.bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
    }

    @Override
    public Book createBook(BookDTO bookDTO, MultipartFile file) throws IOException {
        ImageModel bookImage = this.uploadImage(file);
        bookDTO.setBookImage(bookImage);
        Book book = this.getBookFromDTO(bookDTO);
        book.setCreatedDate(new Date());
        book.setModifiedDate(new Date());
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookDTO bookDTO) {
        Book updateBook = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        updateBook.setBookName(bookDTO.getBookName());
        updateBook.setBookDesc(bookDTO.getBookDesc());
        updateBook.setAuthor(bookDTO.getAuthor());
        updateBook.setPrice(bookDTO.getPrice());
        updateBook.setCategory(this.categoryService.getCategoryById(bookDTO.getCategoryId()));
        updateBook.setModifiedDate(new Date());
        return this.bookRepository.save(updateBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        this.bookRepository.delete(book);
    }
}
