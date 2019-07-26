package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;
    private String lname;
    private String fname;

    @ManyToMany
    @JoinTable(name = "wrote",
                joinColumns = {@JoinColumn(name = "authorid")},
                inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("author")

    private List<Book> book = new ArrayList<>();

    public Author()
    {
    }

    public Author(String lname, String fname)
    {
        this.lname = lname;
        this.fname = fname;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lname;
    }

    public void setLastname(String lname)
    {
        this.lname = lname;
    }

    public String getFirstname()
    {
        return fname;
    }

    public void setFirstname(String fname)
    {
        this.fname = fname;
    }

    public List<Book> getBook()
    {
        return book;
    }

    public void setBook(List<Book> book)
    {
        this.book = book;
    }
}
