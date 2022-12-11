package com.magicofbooks.backend.repository;

import com.magicofbooks.backend.model.CompletedList;
import com.magicofbooks.backend.model.CompletedListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CompletedListItemRepository extends JpaRepository<CompletedListItem, Long> {
    Set<CompletedListItem> findByCompletedList(CompletedList completedList);

    CompletedListItem findByIdAndCompletedList(Long id, CompletedList completedList);
}
