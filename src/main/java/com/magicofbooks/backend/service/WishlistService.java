package com.magicofbooks.backend.service;

import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.Wishlist;
import com.magicofbooks.backend.model.WishlistItem;

import java.util.List;

public interface WishlistService {

    Wishlist createWishlist(Wishlist wishlist);

    Wishlist findByUser(User user);
}
