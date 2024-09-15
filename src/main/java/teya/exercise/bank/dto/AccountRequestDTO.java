package teya.exercise.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequestDTO {
    @JsonProperty("userId")
    @NotNull(message = "User Id is required")
    private UUID userId;

    @JsonProperty("balance")
    @DecimalMin(value = "0.0", message = "The value must be positive")
    private double balance;
}
