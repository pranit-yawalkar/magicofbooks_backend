package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.ResponseDTO;
import com.magicofbooks.backend.dto.WishlistItemDTO;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.model.WishlistItem;
import com.magicofbooks.backend.service.impl.WishlistItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistItemServiceImpl wishlistItemService;

    @GetMapping
    public Set<WishlistItemDTO> getAllWishlistItems(Principal principal) {
        return this.wishlistItemService.getAllItemsByUser(principal);
    }

    @PostMapping("/add")
    public ResponseEntity<WishlistItem> addToWishlist(Principal principal, @RequestBody BookDTO book) {
        WishlistItem wishlistItem1 = this.wishlistItemService.addToWishlist(principal, book);
        return new ResponseEntity<>(wishlistItem1, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{wishlistItemId}")
    public ResponseEntity<ResponseDTO> removeFromWishlist(Principal principal, @PathVariable Long wishlistItemId) {
        this.wishlistItemService.deleteWishlistItem(principal, wishlistItemId);
        ResponseDTO responseDTO = new ResponseDTO(true, "item removed from wishlist!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
