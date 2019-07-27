package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
    List<Book> findAll(Pageable pageable);

    Book update(Book book, long id);

    Book save (Book book);

    Book updateBookToAuthor(long bookid, long authorid);

    void delete(long id);

}
