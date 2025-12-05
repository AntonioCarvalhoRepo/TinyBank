package teya.exercise.bank.gateway;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import teya.exercise.bank.dto.UserRequestDTO;
import teya.exercise.bank.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGateway {

    private final UserService userService;

    public UserGateway(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content) })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody UserRequestDTO user){
        return userService.create(user);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUser(@PathVariable UUID id) {
        userService.disable(id);
    }

}