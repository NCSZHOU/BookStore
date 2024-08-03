package com.zand.bookstore.repository;

import com.zand.bookstore.entity.Book;
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
//        bookRepository.updateBookStock("E38-0542-9802", 40);
//        String quantity = bookRepository.findBookQuantityByBookId(userId);
//        Assertions.assertEquals(40, Integer.parseInt(quantity));
    }
}