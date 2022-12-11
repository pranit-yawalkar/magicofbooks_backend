package com.magicofbooks.backend.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comp_list_item")
public class CompletedListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_added")
    private Date date;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "compId")
    private CompletedList completedList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CompletedList getCompletedList() {
        return completedList;
    }

    public void setCompletedList(CompletedList completedList) {
        this.completedList = completedList;
    }
}
