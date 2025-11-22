package com.assignment.internaltransfer.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.assignment.internaltransfer.model.Account;
import com.assignment.internaltransfer.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @Mock private AccountRepository accountRepository;

  @InjectMocks private AccountService accountService;

  @Test
  void createAccount_success() {
    when(accountRepository.existsById(1L)).thenReturn(false);

    accountService.createAccount(1L, new BigDecimal("100.00"));

    verify(accountRepository).save(ArgumentMatchers.eq(new Account(1L, new BigDecimal("100.00"))));
  }

  @Test
  void createAccount_existing_throws() {
    when(accountRepository.existsById(1L)).thenReturn(true);

    assertThatThrownBy(() -> accountService.createAccount(1L, BigDecimal.TEN))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Account already exists");
  }

  @Test
  void getAccount_notFound_throws() {
    when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> accountService.getAccount(1L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Account not found");
  }

  @Test
  void updateBalance_negative_throws() {
    assertThatThrownBy(() -> accountService.updateBalance(1L, new BigDecimal("-10.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Balance cannot be negative");
  }

  @Test
  void updateBalance_success() {
    Account account = new Account(1L, new BigDecimal("50.00"));
    when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

    accountService.updateBalance(1L, new BigDecimal("200.00"));

    verify(accountRepository).findById(1L);
  }
}
