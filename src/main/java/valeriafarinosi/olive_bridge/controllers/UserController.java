package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.UpdateUserRequestDTO;
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
                user.getBusinessName(),
                user.getBusinessTaxId(),
                user.getStatus()
        );
    }

    @PutMapping("/me")
    public UserResponseDTO updateCurrentUser(
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequestDTO payload
    ) {

        User currentUser = (User) authentication.getPrincipal();

        User updatedUser = userService.updateCurrentUser(
                currentUser.getUserId(),
                payload
        );

        return new UserResponseDTO(
                updatedUser.getUserId(),
                updatedUser.getName(),
                updatedUser.getSurname(),
                updatedUser.getEmail(),
                updatedUser.getRole().getName(),
                updatedUser.getAccountType(),
                updatedUser.getBusinessName(),
                updatedUser.getBusinessTaxId(),
                updatedUser.getStatus()
        );
    }
}