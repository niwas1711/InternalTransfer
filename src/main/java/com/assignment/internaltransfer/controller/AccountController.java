package com.assignment.internaltransfer.controller;

import com.assignment.internaltransfer.model.*;
import com.assignment.internaltransfer.service.AccountService;
import jakarta.validation.Valid;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAccount(@Valid @RequestBody CreateAccountRequest request) {
        BigDecimal initialBalance = new BigDecimal(request.getInitial_balance());
        log.debug("Request to create account {} with balance {}", request.getAccount_id(), initialBalance);
        accountService.createAccount(request.getAccount_id(), initialBalance);
    }

    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        log.debug("Request to get account {}", accountId);
        Account account = accountService.getAccount(accountId);
        return new AccountResponse(account.getId(), account.getBalance().toPlainString());
    }

    @PutMapping("/{accountId}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(
            @PathVariable Long accountId, @Valid @RequestBody UpdateBalanceRequest request) {
        BigDecimal newBalance = new BigDecimal(request.getBalance());
        log.debug("Request to update balance for account {} to {}", accountId, newBalance);
        accountService.updateBalance(accountId, newBalance);
    }
}

