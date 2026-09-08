package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(

        @NotBlank(message = "Address label is required.")
        String label,

        @NotBlank(message = "Recipient name is required.")
        String recipientName,

        @NotBlank(message = "Postal code is required.")
        String postalCode,

        @NotBlank(message = "Prefecture is required.")
        String prefecture,

        @NotBlank(message = "City is required.")
        String city,

        @NotBlank(message = "Area is required.")
        String area,

        @NotBlank(message = "Street is required.")
        String street,

        String building
) {
}