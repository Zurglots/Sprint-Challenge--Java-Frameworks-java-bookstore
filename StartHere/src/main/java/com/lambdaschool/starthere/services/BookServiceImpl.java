package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepos
                .findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if (book.getBooktitle() != null)
        {
            if (book.getBooktitle() != null)
            {
                currentBook.setBooktitle(book.getBooktitle());
            }

            if (book.getIsbn() != null)
            {
                currentBook.setIsbn(book.getIsbn());
            }

            if (book.getCopydate() != 0)
            {
                currentBook.setCopydate(book.getCopydate());
            }
        }
        return bookrepos.save(currentBook);
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();

        newBook.setBooktitle(book.getBooktitle());
        newBook.setCopydate(book.getCopydate());
        newBook.setIsbn(book.getIsbn());

        ArrayList<Author> newAuthor = new ArrayList<>();
        for (Author a : book.getAuthor())
        {
            newAuthor.add(new Author(a.getLastname(), a.getFirstname()));
        }
        newBook.setAuthor(newAuthor);

        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public Book updateBookToAuthor(long bookid, long authorid)
    {
        Book currentBook = bookrepos.findBookId(bookid);
        Author currentAuthor = authorrepos.findAuthorId(authorid);

        currentBook.getAuthor().add(currentAuthor);

        return currentBook;
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
