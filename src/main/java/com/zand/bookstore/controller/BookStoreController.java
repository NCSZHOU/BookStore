package com.zand.bookstore.controller;

import com.zand.bookstore.dao.BookDao;
import com.zand.bookstore.entity.Book;
import com.zand.bookstore.entity.BookSearchRequestVO;
import com.zand.bookstore.service.BookStoreService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(name = "BookStoreControllerApi", description = "Book Store controller api",
     externalDocs = @ExternalDocumentation(description = "Book store related apis, support retrieve/add books", url = ""))
public class BookStoreController {
    private final BookStoreService bookStoreService;

    @Operation(summary = "Retrieve all available books ",
            description = "Retrieve all available books, the book's status is available and the stock of book bigger than 0",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Failed",
                            content = @Content(mediaType = "application/json")
                    )}
    )
    @GetMapping("/retrieve-all")
    public List<Book> retrieveBooks() {
        log.info("Start to retrieve all available books .");
        return bookStoreService.retrieveAllBooks();
    }

    @Operation(summary = "add a new book",
            description = "add a new book",
            parameters = {@Parameter(name = "Book",required = true)},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Failed",
                            content = @Content(mediaType = "application/json")
                    )}
    )
    @PostMapping("/add")
    public void addBooks(@RequestBody BookDao book) {
        log.info("Start to save book ...");
        bookStoreService.saveBook(book);
    }
}
