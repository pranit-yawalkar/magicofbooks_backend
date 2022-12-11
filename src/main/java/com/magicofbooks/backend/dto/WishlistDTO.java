package com.magicofbooks.backend.dto;

public class WishlistDTO {
    private Long wishlistId;
    private Long userId;

    public WishlistDTO() {
    }

    public WishlistDTO(Long wishlistId, Long userId) {
        this.wishlistId = wishlistId;
        this.userId = userId;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
