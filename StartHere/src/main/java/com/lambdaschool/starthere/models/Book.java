package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column
    private String title;

    private String isbn;
    private int copy;

    @ManyToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Author> author = new ArrayList<>();

    public Book()
    {
    }

    public Book(String title, String isbn, int copy, List<Author> author)
    {
        this.title = title;
        this.isbn = isbn;
        this.copy = copy;
        this.author = author;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return title;
    }

    public void setBooktitle(String title)
    {
        this.title = title;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopydate()
    {
        return copy;
    }

    public void setCopydate(int copy)
    {
        this.copy = copy;
    }

    public List<Author> getAuthor()
    {
        return author;
    }

    public void setAuthor(List<Author> author)
    {
        this.author = author;
    }
}
