package com.magicofbooks.backend.service;


import com.magicofbooks.backend.model.CompletedList;
import com.magicofbooks.backend.model.User;

public interface CompletedListService {
    CompletedList createCompList(CompletedList completedList);
    CompletedList getByUser(User user);
}
