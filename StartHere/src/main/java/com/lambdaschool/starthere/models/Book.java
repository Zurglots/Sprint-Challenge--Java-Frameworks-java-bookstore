package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column(nullable = false)
    private String booktitle;

    private String isbn;
    private int copydate;

    @ManyToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Author> author = new ArrayList<>();

    public Book()
    {
    }

    public Book(String booktitle, String isbn, int copydate, List<Author> author)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copydate = copydate;
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
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
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
        return copydate;
    }

    public void setCopydate(int copydate)
    {
        this.copydate = copydate;
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
