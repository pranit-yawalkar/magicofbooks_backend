package com.magicofbooks.backend.dto;

import com.magicofbooks.backend.model.ImageModel;
import com.sun.istack.NotNull;

import java.util.Date;

public class BookDTO {
    private Long bookId;
    private @NotNull String bookName;
    private @NotNull String bookDesc;
    private @NotNull String author;
    private @NotNull double price;
    private @NotNull Integer quantity;
    private ImageModel bookImage;
    private @NotNull Long categoryId;

    private Date createdDate;

    private Date modifiedDate;

    public BookDTO() {
    }

    public BookDTO(Long bookId, String bookName, String bookDesc, String author, double price, ImageModel bookImage, Long categoryId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookDesc = bookDesc;
        this.author = author;
        this.price = price;
        this.bookImage = bookImage;
        this.categoryId = categoryId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ImageModel getBookImage() {
        return bookImage;
    }

    public void setBookImage(ImageModel bookImage) {
        this.bookImage = bookImage;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
