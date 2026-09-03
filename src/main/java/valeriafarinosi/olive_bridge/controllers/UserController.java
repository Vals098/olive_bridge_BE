package valeriafarinosi.olive_bridge.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.UserResponseDTO;
import valeriafarinosi.olive_bridge.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

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