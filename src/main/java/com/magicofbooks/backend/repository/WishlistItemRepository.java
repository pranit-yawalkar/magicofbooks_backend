package com.magicofbooks.backend.repository;

import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.Wishlist;
import com.magicofbooks.backend.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    Set<WishlistItem> findByWishlist(Wishlist wishlist);
    WishlistItem findByIdAndWishlist(Long wishlistItemId, Wishlist wishlist);
}
