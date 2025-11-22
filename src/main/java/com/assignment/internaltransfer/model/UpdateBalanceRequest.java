package com.assignment.internaltransfer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to update the balance of an account")
public class UpdateBalanceRequest {

    @NotNull
    @Pattern(
            regexp = "^\\d+(\\.\\d+)?$",
            message = "balance must be a non-negative decimal number")
    @Schema(
            description = "New balance for the account, non-negative decimal as string",
            example = "250.00")
    private String balance;
}
