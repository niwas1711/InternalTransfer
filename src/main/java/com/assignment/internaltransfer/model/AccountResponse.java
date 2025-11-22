package com.assignment.internaltransfer.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountResponse {
  Long account_id;
  String balance;
}
