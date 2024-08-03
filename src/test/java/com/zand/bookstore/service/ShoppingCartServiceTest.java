package com.zand.bookstore.service;

import com.zand.bookstore.common.CommonResponse;
import com.zand.bookstore.repository.BookRepository;
import com.zand.bookstore.repository.CategoryRepository;
import com.zand.bookstore.repository.ShoppingCartRepository;
import com.zand.bookstore.testData.TestDataBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {
    private ShoppingCartService shoppingCartService;
    private ShoppingCartRepository shoppingCartRepository;
    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private String userId = "404249022";

    @BeforeEach
    public void setUp() {
        // Create mock object
        bookRepository = Mockito.mock(BookRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);
        shoppingCartService = new ShoppingCartService(shoppingCartRepository, bookRepository, categoryRepository);
    }

    @Test
    void whenSaveShoppingCart_thenReturnSuccessResponseAndSaveGoods() {
        Mockito.when(shoppingCartRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        CommonResponse expectResponse = CommonResponse.response(HttpStatus.OK.value(), HttpStatus.OK.toString(), TestDataBooks.generateBookDaoList().size());
        CommonResponse ActualResponse = shoppingCartService.save(userId, TestDataBooks.generateBookDaoList());

        Assertions.assertEquals(expectResponse.getData(), ActualResponse.getData());
        Assertions.assertEquals(expectResponse.getCode(), ActualResponse.getCode());
        Assertions.assertEquals(expectResponse.getMsg(), ActualResponse.getMsg());
    }

    @Test
    void whenCheckout_thenReturnTotalAmount() {
        double totalAmount = TestDataBooks.getTestBooksTotalAmount();
        CommonResponse commonResponse = shoppingCartService.checkout(userId, TestDataBooks.generateBookDaoList());
        Assertions.assertEquals(totalAmount, commonResponse.getData());
    }

    @Test
    void retrieveCartByUserId() {
    }
}