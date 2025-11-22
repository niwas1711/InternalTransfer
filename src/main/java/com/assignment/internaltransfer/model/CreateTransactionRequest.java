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
@Schema(description = "Request to transfer amount between two accounts")
public class CreateTransactionRequest {

    @NotNull
    @Schema(description = "Source account ID", example = "123")
    private Long source_account_id;

    @NotNull
    @Schema(description = "Destination account ID", example = "456")
    private Long destination_account_id;

    @NotNull
    @Pattern(
            regexp = "^\\d+(\\.\\d+)?$",
            message = "amount must be a non-negative decimal number")
    @Schema(
            description = "Amount to transfer, positive decimal as string",
            example = "50.75")
    private String amount;
}
