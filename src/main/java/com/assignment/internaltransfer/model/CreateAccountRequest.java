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
@Schema(description = "Request to create a new account")
public class CreateAccountRequest {

    @NotNull
    @Schema(description = "Unique account identifier", example = "123")
    private Long account_id;

    @NotNull
    @Pattern(
            regexp = "^\\d+(\\.\\d+)?$",
            message = "initial_balance must be a non-negative decimal number")
    @Schema(
            description = "Initial balance for the account, non-negative decimal as string",
            example = "100.25")
    private String initial_balance;
}
