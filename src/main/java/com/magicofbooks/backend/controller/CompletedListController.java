package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.CompletedListItemDTO;
import com.magicofbooks.backend.dto.ResponseDTO;
import com.magicofbooks.backend.model.CompletedListItem;
import com.magicofbooks.backend.service.impl.CompletedListItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/completed_lists")
public class CompletedListController {

    @Autowired
    private CompletedListItemServiceImpl completedListItemService;

    @GetMapping
    public ResponseEntity<Set<CompletedListItemDTO>> getAllItems(Principal principal) {
        Set<CompletedListItemDTO> completedListItemDTOS =
                this.completedListItemService.getAllItems(principal);
        return new ResponseEntity<>(completedListItemDTOS, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CompletedListItem> addToCompletedList(Principal principal, @RequestBody BookDTO bookDTO) {
         CompletedListItem completedListItem =
                 this.completedListItemService.addToCompletedList(principal, bookDTO);
         return new ResponseEntity<>(completedListItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{completedListItemId}")
    public ResponseEntity<ResponseDTO> deleteFromCompletedList(Principal principal, @PathVariable Long completedListItemId) {
        this.completedListItemService.deleteFromCompletedList(principal, completedListItemId);
        ResponseDTO responseDTO = new ResponseDTO(true, "Item removed successfully!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
