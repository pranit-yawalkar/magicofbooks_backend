package com.magicofbooks.backend.service;


import com.magicofbooks.backend.dto.BookDTO;
import com.magicofbooks.backend.dto.CompletedListItemDTO;
import com.magicofbooks.backend.model.CompletedListItem;

import java.security.Principal;
import java.util.Set;

public interface CompletedListItemService {
    Set<CompletedListItemDTO> getAllItems(Principal principal);
    CompletedListItem addToCompletedList(Principal principal, BookDTO bookDTO);
    void deleteFromCompletedList(Principal principal, Long compListItemId);
}
