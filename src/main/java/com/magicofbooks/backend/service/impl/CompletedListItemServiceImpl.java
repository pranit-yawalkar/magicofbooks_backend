package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.CompletedListItemDTO;
import com.magicofbooks.backend.exception.CustomException;
import com.magicofbooks.backend.model.Book;
import com.magicofbooks.backend.model.CompletedList;
import com.magicofbooks.backend.model.CompletedListItem;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.repository.CompletedListItemRepository;
import com.magicofbooks.backend.service.CompletedListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CompletedListItemServiceImpl implements CompletedListItemService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private CompletedListServiceImpl completedListService;

    @Autowired
    private CompletedListItemRepository completedListItemRepository;

    public CompletedListItemDTO getCompletedListItemDTO(CompletedListItem completedListItem) {
        CompletedListItemDTO completedListItemDTO = new CompletedListItemDTO();
        completedListItemDTO.setId(completedListItem.getId());
        completedListItemDTO.setBook(completedListItem.getBook());
        completedListItemDTO.setDate(completedListItem.getDate());
        completedListItemDTO.setCompletedListDTO(
                this.completedListService.getCompletedListDTO(completedListItem.getCompletedList()));
        return completedListItemDTO;
    }

    @Override
    public Set<CompletedListItemDTO> getAllItems(Principal principal) {
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to see the completed list.");
        }
        User user = this.jwtService.getUserFromUserDetails(userDetails);
        CompletedList completedList = this.completedListService.getByUser(user);
        Set<CompletedListItem> completedListItems =
                this.completedListItemRepository.findByCompletedList(completedList);
        Set<CompletedListItemDTO> completedListItemDTOS = new HashSet<>();
        for(CompletedListItem completedListItem: completedListItems) {
            CompletedListItemDTO completedListItemDTO = this.getCompletedListItemDTO(completedListItem);
            completedListItemDTOS.add(completedListItemDTO);
        }
        return completedListItemDTOS;
    }

    @Override
    public CompletedListItem addToCompletedList(Principal principal, BookDTO bookDTO) {
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to see the completed list.");
        }
        User user = this.jwtService.getUserFromUserDetails(userDetails);
        CompletedList completedList = this.completedListService.getByUser(user);
        Set<CompletedListItem> completedListItems =
                this.completedListItemRepository.findByCompletedList(completedList);
        for(CompletedListItem completedListItem : completedListItems){
            if(Objects.equals(completedListItem.getBook().getBookId(), bookDTO.getBookId())) {
                throw new CustomException("Item already exists in the completed list.");
            }
        }
        CompletedListItem completedListItem = new CompletedListItem();
        Book book = this.bookService.getBookById(bookDTO.getBookId());
        completedListItem.setBook(book);
        completedListItem.setCompletedList(completedList);
        completedListItem.setDate(new Date());
        return this.completedListItemRepository.save(completedListItem);
    }

    @Override
    public void deleteFromCompletedList(Principal principal, Long compListItemId) {
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) this.jwtService.loadUserByUsername(principal.getName());
        if(userDetails==null) {
            throw new CustomException("Please login to see the completed list.");
        }
        User user = this.jwtService.getUserFromUserDetails(userDetails);
        CompletedList completedList = this.completedListService.getByUser(user);
        CompletedListItem completedListItem =
                this.completedListItemRepository.findByIdAndCompletedList(compListItemId, completedList);
        this.completedListItemRepository.delete(completedListItem);
    }
}
