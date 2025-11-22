package com.assignment.internaltransfer.service;

import com.assignment.internaltransfer.model.Account;
import com.assignment.internaltransfer.model.TransactionStatus;
import com.assignment.internaltransfer.model.Transactions;
import com.assignment.internaltransfer.repository.AccountRepository;
import com.assignment.internaltransfer.repository.TransactionsRepository;

import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionLogRepository;

    @Transactional
    public void transfer(Long sourceId, Long destId, BigDecimal amount) {
        if (sourceId.equals(destId)) {
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        log.debug("Transfer requested: {} -> {} amount {}", sourceId, destId, amount);

        Transactions transactions;
        try {
            Account source =
                    accountRepository
                            .findByIdForUpdate(sourceId)
                            .orElseThrow(
                                    () -> new IllegalArgumentException("Source account not found: " + sourceId));
            Account dest =
                    accountRepository
                            .findByIdForUpdate(destId)
                            .orElseThrow(
                                    () -> new IllegalArgumentException("Destination account not found: " + destId));

            if (source.getBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance in source account");
            }

            source.setBalance(source.getBalance().subtract(amount));
            dest.setBalance(dest.getBalance().add(amount));

            transactions =
                    Transactions.builder()
                            .sourceAccountId(sourceId)
                            .destinationAccountId(destId)
                            .amount(amount)
                            .status(TransactionStatus.SUCCESS)
                            .build();

            log.info("Transfer successful: {} -> {} amount {}", sourceId, destId, amount);
        } catch (RuntimeException ex) {
            log.error(
                    "Transfer failed: {} -> {} amount {}. Reason: {}",
                    sourceId,
                    destId,
                    amount,
                    ex.getMessage());
            transactions =
                    Transactions.builder()
                            .sourceAccountId(sourceId)
                            .destinationAccountId(destId)
                            .amount(amount)
                            .status(TransactionStatus.FAILED)
                            .failureReason(ex.getMessage())
                            .build();
            transactionLogRepository.save(transactions);
            throw ex;
        }

        transactionLogRepository.save(transactions);
    }
}
