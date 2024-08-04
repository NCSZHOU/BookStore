package com.greyson.bookstore.repository;

import com.greyson.bookstore.entity.ShoppingCart;
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
class ShoppingCartRepositoryTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    private final String userId = "404249022";

    @Test
    void whenFindAllByUserId_thenReturnShoppingCart() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllByUserId(userId);
        Assertions.assertTrue(shoppingCartList.size() > 0);
    }

    @Transactional
    @Rollback
    @Test
    void deleteGoodsByUserId() {
        int status = shoppingCartRepository.deleteGoodsByUserId(userId);
        Assertions.assertNotEquals(0, status);
    }
}