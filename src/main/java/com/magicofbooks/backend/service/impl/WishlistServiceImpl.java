package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.WishlistDTO;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.model.Wishlist;
import com.magicofbooks.backend.repository.WishlistRepository;
import com.magicofbooks.backend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public WishlistDTO getWishlistDTO(Wishlist wishlist) {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlistId(wishlist.getWishlistId());
        wishlistDTO.setUserId(wishlist.getUser().getUserId());
        return wishlistDTO;
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist findByUser(User user) {
        return this.wishlistRepository.findByUser(user);
    }


}
