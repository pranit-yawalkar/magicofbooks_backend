package com.magicofbooks.backend.service;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.WishlistItemDTO;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.Wishlist;
import com.magicofbooks.backend.model.WishlistItem;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface WishlistItemService {
    Set<WishlistItemDTO> getAllItemsByUser(Principal principal);
    WishlistItem addToWishlist(Principal principal, BookDTO book);
    void deleteWishlistItem(Principal principal, Long wishlistItemId);
}
