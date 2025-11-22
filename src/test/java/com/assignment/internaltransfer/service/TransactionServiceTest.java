package com.assignment.internaltransfer.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.assignment.internaltransfer.model.Account;
import com.assignment.internaltransfer.model.Transactions;
import com.assignment.internaltransfer.repository.AccountRepository;
import com.assignment.internaltransfer.repository.TransactionsRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @Mock private AccountRepository accountRepository;

  @Mock private TransactionsRepository transactionsRepository;

  @InjectMocks private TransactionService transactionService;

  @Test
  void transfer_success() {
    Account source = new Account(1L, new BigDecimal("100.00"));
    Account dest = new Account(2L, new BigDecimal("0.00"));

    when(accountRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(source));
    when(accountRepository.findByIdForUpdate(2L)).thenReturn(Optional.of(dest));

    transactionService.transfer(1L, 2L, new BigDecimal("50.00"));

    ArgumentCaptor<Transactions> captor = ArgumentCaptor.forClass(Transactions.class);
    verify(transactionsRepository, times(1)).save(captor.capture());
  }

  @Test
  void transfer_insufficientBalance_throws() {
    Account source = new Account(1L, new BigDecimal("10.00"));
    Account dest = new Account(2L, new BigDecimal("0.00"));

    when(accountRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(source));
    when(accountRepository.findByIdForUpdate(2L)).thenReturn(Optional.of(dest));

    assertThatThrownBy(() -> transactionService.transfer(1L, 2L, new BigDecimal("50.00")))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Insufficient balance");
  }
}
