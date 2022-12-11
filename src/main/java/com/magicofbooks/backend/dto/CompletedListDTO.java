package com.magicofbooks.backend.dto;

public class CompletedListDTO {
    private Long compListId;
    private Long userId;

    public Long getCompListId() {
        return compListId;
    }

    public void setCompListId(Long compListId) {
        this.compListId = compListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
