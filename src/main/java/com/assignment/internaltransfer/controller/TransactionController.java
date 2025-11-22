package com.assignment.internaltransfer.controller;

import com.assignment.internaltransfer.model.CreateTransactionRequest;
import com.assignment.internaltransfer.service.TransactionService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
    BigDecimal amount = new BigDecimal(request.getAmount());
    transactionService.transfer(
        request.getSource_account_id(), request.getDestination_account_id(), amount);
  }
}
