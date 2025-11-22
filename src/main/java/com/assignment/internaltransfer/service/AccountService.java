package com.assignment.internaltransfer.service;

import com.assignment.internaltransfer.model.Account;
import com.assignment.internaltransfer.repository.AccountRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

  private final AccountRepository accountRepository;

  @Transactional
  public void createAccount(Long accountId, BigDecimal initialBalance) {
    if (accountRepository.existsById(accountId)) {
      throw new IllegalArgumentException("Account already exists: " + accountId);
    }
    if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Initial balance cannot be negative");
    }
    Account account = new Account(accountId, initialBalance);
    accountRepository.save(account);
    log.info("Created account {} with balance {}", accountId, initialBalance);
  }

  @Transactional(readOnly = true)
  public Account getAccount(Long accountId) {
    return accountRepository
        .findById(accountId)
        .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));
  }

  @Transactional
  public void updateBalance(Long accountId, BigDecimal newBalance) {
    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");
    }

    Account account =
        accountRepository
            .findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));

    account.setBalance(newBalance);
    log.info("Updated balance for account {} to {}", accountId, newBalance);
  }
}
