/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */

package es.deusto.sd.strava.facade;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.UserDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.dto.UserAssembler;
import es.deusto.sd.strava.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization Controller", description = "User registration, login, and logout operations")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
        summary = "Register a new user",
        description = "Allows a new user to register by providing profile information.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created: Registration successful"),
            @ApiResponse(responseCode = "400", description = "Bad Request: Invalid input or email already exists")
        }
    )
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Parameter(name = "user", description = "User profile data", required = true)
                                            @RequestBody UserDTO userDTO) {
        try {
            User user = UserAssembler.toEntity(userDTO);
            authService.register(user);
            UserDTO responseDTO = UserAssembler.toDTO(user);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Operation(
        summary = "Login to the system",
        description = "Allows a user to login by providing email and account type (Google or Meta). Returns a token if successful.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK: Login successful, returns a token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid credentials, login failed")
        }
    )
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Parameter(name = "credentials", description = "User's email and account type", required = true)
                                         @RequestBody UserDTO userDTO) {
        try {
            User user = UserAssembler.toEntity(userDTO);
            Optional<String> token = authService.login(user.getEmail(), user.getTipoUsuario());

            if (token.isPresent()) {
                User loggedInUser = authService.getUserByEmail(user.getEmail());
                UserDTO responseDTO = UserAssembler.toDTO(loggedInUser);

                return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token.get())
                    .body(responseDTO);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
        summary = "Logout from the system",
        description = "Allows a user to logout by providing the authorization token.",
        responses = {
            @ApiResponse(responseCode = "204", description = "No Content: Logout successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid token, logout failed")
        }
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Parameter(name = "token", description = "Authorization token", required = true)
                                        @RequestBody String token) {
        Optional<Boolean> result = authService.logout(token);
        if (result.isPresent() && result.get()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
