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
import teya.exercise.bank.dto.TransactionDTO;
import teya.exercise.bank.service.TransactionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionGateway {

    @Autowired
    TransactionService transactionService;

    @Operation(summary = "Create Transaction, support DEPOSIT, WITHDRAW and TRANSFER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Transaction Created",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO transaction){
        transactionService.createTransaction(transaction);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "List Transactions by Account Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of Transactions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @GetMapping(path = "/{accountId}")
    public ResponseEntity<?> getTransactionByAccountId(@PathVariable UUID accountId){
        return  ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionByAccountId(accountId));
    }
}
