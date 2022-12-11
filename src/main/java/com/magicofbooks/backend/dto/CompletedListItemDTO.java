package com.magicofbooks.backend.dto;

import com.magicofbooks.backend.model.Book;

import java.util.Date;

public class CompletedListItemDTO {

    private Long id;
    private Date date;
    private Book book;
    private CompletedListDTO completedListDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CompletedListDTO getCompletedListDTO() {
        return completedListDTO;
    }

    public void setCompletedListDTO(CompletedListDTO completedListDTO) {
        this.completedListDTO = completedListDTO;
    }
}
