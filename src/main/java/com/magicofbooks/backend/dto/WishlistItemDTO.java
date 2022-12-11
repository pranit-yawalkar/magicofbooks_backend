package com.magicofbooks.backend.dto;

import com.magicofbooks.backend.model.Book;

import java.util.Date;

public class WishlistItemDTO {
    private Long id;
    private Date date;
    private Book book;
    private WishlistDTO wishlistDTO;

    public WishlistItemDTO() {
    }

    public WishlistItemDTO(Long id, Date date, Book book, WishlistDTO wishlistDTO) {
        this.id = id;
        this.date = date;
        this.book = book;
        this.wishlistDTO = wishlistDTO;
    }

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

    public WishlistDTO getWishlistDTO() {
        return wishlistDTO;
    }

    public void setWishlistDTO(WishlistDTO wishlistDTO) {
        this.wishlistDTO = wishlistDTO;
    }
}
