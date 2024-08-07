package com.greyson.bookstore.repository;

import com.greyson.bookstore.entity.Book;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    private final String bookID = "E38-0542-9802";

    @Test
    void whenFindAllAvailableBooks_thenReturnBooks() {
        List<Book> bookList = bookRepository.findAllAvailableBooks();
        Assertions.assertNotEquals(0, bookList.size());
    }

    @Test
    void whenFindBookQuantityByBookId_thenReturnBookQuantity() {
        String quantity = bookRepository.findBookQuantityByBookId(bookID);
        Assertions.assertNotNull(quantity);
    }

    @Test
    void wheFindBookByBookId_thenReturnBook() {
        Book book = bookRepository.findBookByBookId(bookID);
        Assertions.assertNotNull(book);
    }

    @Transactional
    @Rollback(true)
    @Test
    void whenUpdateBookStock_thenStockWasUpdated() {
        int status = bookRepository.updateBookStock("E38-0542-9802", 2);
        Assertions.assertNotEquals(0, status);
    }
}