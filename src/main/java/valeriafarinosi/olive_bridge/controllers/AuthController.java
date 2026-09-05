package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.LoginRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.RegisterRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.UserResponseDTO;
import valeriafarinosi.olive_bridge.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequestDTO body) {
        return authService.authenticateAndGenerateToken(body);
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO body) {
        User user = authService.register(body);

        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole().getName(),
                user.getAccountType(),
                user.getStatus()
        );
    }
}