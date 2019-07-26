package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Returns all Books with Paging Ability", responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +

                            "Multiple sort criteria are supported.")})

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Books Found", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/books",
                produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 3) Pageable pageable, HttpServletRequest request)
    {
        logger.trace(request.getRequestURI() + " accessed");
        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Updated", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Updated", response = ErrorDetail.class)
    })
    @PutMapping(value = "data/books/{id}")
    public ResponseEntity<?> updateBook(HttpServletRequest request,
                                        @RequestBody
                                                Book updateBook,
                                        @ApiParam(value = "book id", example = "1")
                                        @PathVariable
                                                long bookid
                                        )
    {
        logger.trace(request.getRequestURI() + " accessed");
        bookService.update(updateBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Updated", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Updated", response = ErrorDetail.class)
    })

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> updateBookToAuthor(HttpServletRequest request,
                                                @ApiParam(value = "book id", example = "1")
                                                @PathVariable long bookid,
                                                @ApiParam(value = "author id", example = "1")
                                                @PathVariable long authorid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        bookService.updateBookToAuthor(bookid, authorid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Updated", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Updated", response = ErrorDetail.class)
    })
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBookById(HttpServletRequest request,
                                            @ApiParam(value = "book id", example = "1")
                                            @PathVariable long bookid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        bookService.delete(bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
