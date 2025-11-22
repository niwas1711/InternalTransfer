package com.assignment.internaltransfer.repository;

import com.assignment.internaltransfer.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {}
