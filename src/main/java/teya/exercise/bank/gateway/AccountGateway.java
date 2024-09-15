package teya.exercise.bank.gateway;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountGateway {

    @Autowired
    AccountService accountService;

    @Operation(summary = "Create Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Account created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequestDTO account){
        return  ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    @Operation(summary = "Get Account Balance by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of Account Balances for a user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getAccountBalances(@PathVariable UUID userId){
        return  ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountBalances(userId));
    }
}
