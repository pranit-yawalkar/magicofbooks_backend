package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.dto.CompletedListDTO;
import com.magicofbooks.backend.model.CompletedList;
import com.magicofbooks.backend.model.User;
import com.magicofbooks.backend.repository.CompletedListRepository;
import com.magicofbooks.backend.service.CompletedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletedListServiceImpl implements CompletedListService {

    @Autowired
    private CompletedListRepository completedListRepository;

    public CompletedListDTO getCompletedListDTO(CompletedList completedList) {
        CompletedListDTO completedListDTO = new CompletedListDTO();
        completedListDTO.setUserId(completedList.getUser().getUserId());
        completedListDTO.setCompListId(completedList.getCompId());
        return completedListDTO;
    }

    @Override
    public CompletedList createCompList(CompletedList completedList) {
        return this.completedListRepository.save(completedList);
    }

    @Override
    public CompletedList getByUser(User user) {
        return this.completedListRepository.findByUser(user);
    }
}
