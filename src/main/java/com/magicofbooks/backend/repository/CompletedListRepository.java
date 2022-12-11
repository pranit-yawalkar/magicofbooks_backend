package com.magicofbooks.backend.repository;

import com.magicofbooks.backend.model.CompletedList;
import com.magicofbooks.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedListRepository extends JpaRepository<CompletedList, Long> {
    CompletedList findByUser(User user);
}
