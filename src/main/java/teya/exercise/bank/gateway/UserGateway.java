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
import teya.exercise.bank.dto.UserRequestDTO;
import teya.exercise.bank.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGateway {

    @Autowired
    UserService userService;

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO user){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @Operation(summary = "Disable User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "User Disabled",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @PatchMapping("/{id}/disable")
    public ResponseEntity<Void> disableUser(@PathVariable UUID id) {
        userService.disable(id);
        return ResponseEntity.noContent().build();
    }

}