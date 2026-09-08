package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SampleRequestRequestDTO(

        @NotNull(message = "Product id is required.")
        UUID productId,

        @NotBlank(message = "Message is required.")
        String message,

        // SHIPPING ADDRESS

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