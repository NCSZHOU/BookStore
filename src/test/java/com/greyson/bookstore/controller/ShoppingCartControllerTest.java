package com.greyson.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greyson.bookstore.service.ShoppingCartService;
import com.greyson.bookstore.testData.TestDataBooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {
    @MockBean
    private ShoppingCartService svc;
    @Autowired
    private MockMvc mockMvc;
    private final String userId = "testId";

    @Test
    void retrieveCart_Success() throws Exception {
        Mockito.when(svc.retrieveCartByUserId(userId)).thenReturn(TestDataBooks.generateBookDaoList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart/" + userId + "/retrieve"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertTrue(response.getContentAsString().contains("E38-0542-9803"));
    }

    @Test
    void save_Success() throws Exception {
        Mockito.when(svc.save(Mockito.any(), Mockito.any())).thenReturn(TestDataBooks.generateSuccessResp(null));

        // Test list data to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(TestDataBooks.generateBookDaoList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/shopping-cart/" + userId + "/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .param("userId", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertTrue(response.getContentAsString().contains(HttpStatus.OK.toString()));
    }

    @Test
    void whenCheckout_thenReturnTotalAmount() throws Exception {
        double totalAmount = TestDataBooks.getTestBooksTotalAmount();
        Mockito.when(svc.checkout(Mockito.any(), Mockito.anyList())).thenReturn(TestDataBooks.generateSuccessResp(totalAmount));

        // Test list data to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(TestDataBooks.generateBookDaoList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/shopping-cart/" + userId + "/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .param("userId", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertTrue(response.getContentAsString().contains(String.valueOf(totalAmount)));
    }

    @Test
    void whenHealthCheck_thenReturnHealthMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart/health-check"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();
    }
}