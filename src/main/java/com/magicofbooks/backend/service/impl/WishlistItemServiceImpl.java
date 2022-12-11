package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.WishlistDTO;
import com.magicofbooks.backend.dto.WishlistItemDTO;
import com.magicofbooks.backend.exception.CustomException;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.model.CompletedListItem;
import com.magicofbooks.backend.model.Wishlist;
import com.magicofbooks.backend.model.WishlistItem;
import com.magicofbooks.backend.repository.WishlistItemRepository;
import com.magicofbooks.backend.service.WishlistItemService;
import com.magicofbooks.backend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private WishlistServiceImpl wishlistService;

    @Autowired
    private BookServiceImpl bookService;

    public WishlistItemDTO getWishlistItemDTO(WishlistItem wishlistItem) {
        WishlistItemDTO wishlistItemDTO = new WishlistItemDTO();
        wishlistItemDTO.setId(wishlistItem.getId());
        wishlistItemDTO.setWishlistDTO(this.wishlistService.getWishlistDTO(wishlistItem.getWishlist()));
        wishlistItemDTO.setBook(wishlistItem.getBook());
        wishlistItemDTO.setDate(wishlistItem.getDate());
        return wishlistItemDTO;
    }


    @Override
    public Set<WishlistItemDTO> getAllItemsByUser(Principal principal) {
        org.springframework.security.core.userdetails.User userDetails = (User) this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to see the wishlist");
        }
        com.magicofbooks.backend.model.User user = this.jwtService.getUserFromUserDetails(userDetails);
        Wishlist wishlist = this.wishlistService.findByUser(user);
        Set<WishlistItem> wishlistItems = this.wishlistItemRepository.findByWishlist(wishlist);
        Set<WishlistItemDTO> wishlistItemDTOS = new HashSet<>();
        for (WishlistItem wishlistItem : wishlistItems) {
            WishlistItemDTO wishlistItemDTO = this.getWishlistItemDTO(wishlistItem);
            wishlistItemDTOS.add(wishlistItemDTO);
        }
        return wishlistItemDTOS;
    }

    @Override
    public WishlistItem addToWishlist(Principal principal, BookDTO bookDTO) {
        org.springframework.security.core.userdetails.User userDetails = (User) this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to add to wishlist");
        }
        com.magicofbooks.backend.model.User user = this.jwtService.getUserFromUserDetails(userDetails);
        Wishlist wishlist = this.wishlistService.findByUser(user);
        Set<WishlistItem> wishlistListItems =
                this.wishlistItemRepository.findByWishlist(wishlist);
        for(WishlistItem wishlistItem : wishlistListItems){
            if(Objects.equals(wishlistItem.getBook().getBookId(), bookDTO.getBookId())) {
                throw new CustomException("Item already exists in the completed list.");
            }
        }
        WishlistItem wishlistItem = new WishlistItem();
        Book book = this.bookService.getBookById(bookDTO.getBookId());
        wishlistItem.setBook(book);
        wishlistItem.setDate(new Date());
        wishlistItem.setWishlist(wishlist);
        return this.wishlistItemRepository.save(wishlistItem);
    }

    @Override
    public void deleteWishlistItem(Principal principal, Long wishlistItemId) {
        User userDetails = (User) this.jwtService.loadUserByUsername(principal.getName());
        com.magicofbooks.backend.model.User user = this.jwtService.getUserFromUserDetails(userDetails);
        Wishlist wishlist = this.wishlistService.findByUser(user);
        WishlistItem wishlistItem = this.wishlistItemRepository.findByIdAndWishlist(wishlistItemId, wishlist);
        if(wishlistItem==null) {
            throw new CustomException("Item not present in the wishlist!");
        }
        this.wishlistItemRepository.delete(wishlistItem);
    }
}
