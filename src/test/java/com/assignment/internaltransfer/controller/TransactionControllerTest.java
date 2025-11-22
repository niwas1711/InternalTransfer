package com.assignment.internaltransfer.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.internaltransfer.model.CreateTransactionRequest;
import com.assignment.internaltransfer.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TransactionControllerTest {

  private MockMvc mockMvc;
  private TransactionService transactionService;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    transactionService = Mockito.mock(TransactionService.class);
    TransactionController controller = new TransactionController(transactionService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testCreateTransaction() throws Exception {
    CreateTransactionRequest req = new CreateTransactionRequest(1L, 2L, "50.00");

    doNothing().when(transactionService).transfer(1L, 2L, new BigDecimal("50.00"));

    mockMvc
        .perform(
            post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isNoContent());
  }
}
