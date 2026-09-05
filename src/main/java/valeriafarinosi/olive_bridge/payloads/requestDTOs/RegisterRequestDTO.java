package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import valeriafarinosi.olive_bridge.enums.AccountType;

public record RegisterRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Surname is required.")
        String surname,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be valid.")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(min = 8, message = "Password must contain at least 8 characters.")
        String password,

        @NotNull(message = "Account type is required.")
        AccountType accountType

) {
}