package com.greyson.bookstore.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenFindIdCountByName_thenReturnCount() {
        int count = categoryRepository.findIdCountByName("Literature");
        Assertions.assertNotNull(count);
        Assertions.assertTrue(count > 0);
    }

    @Test
    void whenFindIdByName_thenReturnId() {
        Long id = categoryRepository.findIdByName("Literature");
        Assertions.assertNotNull(id);
    }

    @Test
    void whenFindNameByCategoryId() {
        String categoryName = categoryRepository.findNameByCategoryId(1L);
        Assertions.assertNotNull(categoryName);
    }
}