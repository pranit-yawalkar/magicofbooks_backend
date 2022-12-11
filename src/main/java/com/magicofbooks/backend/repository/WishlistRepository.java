package com.magicofbooks.backend.repository;

import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUser(User user);
}
