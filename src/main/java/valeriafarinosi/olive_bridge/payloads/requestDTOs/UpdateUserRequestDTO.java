package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Surname is required.")
        String surname,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be valid.")
        String email,

        String businessName,

        String businessTaxId
) {
}