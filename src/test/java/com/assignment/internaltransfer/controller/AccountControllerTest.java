package com.assignment.internaltransfer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.assignment.internaltransfer.model.Account;
import com.assignment.internaltransfer.model.CreateAccountRequest;
import com.assignment.internaltransfer.model.UpdateBalanceRequest;
import com.assignment.internaltransfer.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AccountControllerTest {

  private MockMvc mockMvc;
  private AccountService accountService;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    accountService = Mockito.mock(AccountService.class);
    AccountController controller = new AccountController(accountService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testCreateAccount() throws Exception {
    CreateAccountRequest req = new CreateAccountRequest(1L, "100.00");

    doNothing().when(accountService).createAccount(1L, new BigDecimal("100.00"));

    mockMvc
        .perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isNoContent());
  }

  @Test
  void testGetAccount() throws Exception {
    Account account = new Account(1L, new BigDecimal("150.00"));
    when(accountService.getAccount(1L)).thenReturn(account);

    mockMvc
        .perform(get("/accounts/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.account_id").value(1))
        .andExpect(jsonPath("$.balance").value("150.00"));
  }

  @Test
  void testUpdateBalance() throws Exception {
    UpdateBalanceRequest req = new UpdateBalanceRequest("500.00");

    doNothing().when(accountService).updateBalance(1L, new BigDecimal("500.00"));

    mockMvc
        .perform(
            put("/accounts/1/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
        .andExpect(status().isNoContent());
  }
}
