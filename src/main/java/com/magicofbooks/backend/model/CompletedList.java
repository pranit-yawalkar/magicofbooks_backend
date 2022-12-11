package com.magicofbooks.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "completed_list")
public class CompletedList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long compId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
