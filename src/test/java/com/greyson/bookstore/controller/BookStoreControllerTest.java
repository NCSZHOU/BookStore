package com.greyson.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greyson.bookstore.common.Constants;
import com.greyson.bookstore.service.BookStoreService;
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
class BookStoreControllerTest {
    @MockBean
    private BookStoreService svc;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenRetrieveBooks_thenReturnBooks() throws Exception {
        Mockito.when(svc.retrieveAllBooks()).thenReturn(TestDataBooks.generateBookList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/book/retrieve-all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertTrue(response.getContentAsString().contains("E38-0542-9803"));
    }

    @Test
    void whenAddBooks_thenReturnSuccessResponseAndAddBooks() throws Exception {
        Mockito.when(svc.saveBooks(Mockito.anyList())).thenReturn(TestDataBooks.generateSuccessResp(null));

        // Test list data to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(TestDataBooks.generateBookDaoList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertTrue(response.getContentAsString().contains(HttpStatus.OK.toString()));
    }

    @Test
    void whenAddBooks_thenReturnFailedResponseAndInterruptAdding() throws Exception {
        Mockito.when(svc.saveBooks(Mockito.anyList())).thenReturn(TestDataBooks.generateFailResp());

        // Test list data to json string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(TestDataBooks.generateInValidatedBookDaoList());

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();
        String message = String.format(Constants.SAVE_RESULT_MESSAGE, 0, 4);
        Assertions.assertTrue(response.getContentAsString().contains(message));
    }

    @Test
    void whenHealthCheck_thenReturnHealthMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/health-check"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();
    }
}