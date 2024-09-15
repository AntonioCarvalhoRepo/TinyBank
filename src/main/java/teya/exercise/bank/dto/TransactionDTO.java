package teya.exercise.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import teya.exercise.bank.type.TransactionType;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {
    @JsonProperty("fromAccountId")
    @NotNull(message = "From Account is required")
    private UUID fromAccountID;

    @JsonProperty("toAccountId")
    private UUID toAccountID;

    @JsonProperty("amount")
    @DecimalMin(value = "1.0", message = "The minimum amount for transfer operation must be 1")
    private double amount;

    @JsonProperty("type")
    @NotNull(message = "Type is required")
    private TransactionType type;
}
