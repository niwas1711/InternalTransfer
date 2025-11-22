package com.assignment.internaltransfer.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transactions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long sourceAccountId;
  private Long destinationAccountId;

  @Column(nullable = false, precision = 19, scale = 8)
  private BigDecimal amount;

  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;

  private String failureReason;
}
