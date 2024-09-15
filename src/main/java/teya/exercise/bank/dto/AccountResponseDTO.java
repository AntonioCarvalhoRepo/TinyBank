package teya.exercise.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDTO {
    @JsonProperty("accountId")
    private UUID accountId;

    @JsonProperty("balance")
    private double balance;
}