package com.greyson.bookstore.service;

import com.greyson.bookstore.entity.Book;
import com.greyson.bookstore.common.CommonResponse;
import com.greyson.bookstore.repository.BookRepository;
import com.greyson.bookstore.repository.CategoryRepository;
import com.greyson.bookstore.testData.TestDataBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookStoreServiceTest {

    private BookStoreService bookStoreService;
    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        // Create mock object
        bookRepository = Mockito.mock(BookRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        bookStoreService = new BookStoreService(bookRepository, categoryRepository);
    }

    @Test
    void whenSaveBooks_thenReturnSuccessResponseAndSaveBooks() {
        Mockito.when(bookRepository.findBookQuantityByBookId(Mockito.any())).thenReturn("1");
        Mockito.when(categoryRepository.findIdCountByName(Mockito.any())).thenReturn(1);
        Mockito.when(categoryRepository.findIdByName(Mockito.any())).thenReturn(1L);
        CommonResponse commonResponse = bookStoreService.saveBooks(TestDataBooks.generateBookDaoList());

        Assertions.assertEquals(HttpStatus.OK.value(), commonResponse.getCode());
    }

    @Test
    void wheRetrieveAllBooks_thenReturnBooks() {
        Mockito.when(bookRepository.findAllAvailableBooks()).thenReturn(TestDataBooks.generateBookList());
        List<Book> bookList = bookStoreService.retrieveAllBooks();
        Assertions.assertEquals(TestDataBooks.generateBookList().size(), bookList.size());
    }
}