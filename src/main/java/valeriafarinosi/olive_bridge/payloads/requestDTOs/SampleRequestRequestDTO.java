package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SampleRequestRequestDTO(

        @NotNull(message = "Product id is required.")
        UUID productId,

        @NotBlank(message = "Message is required.")
        String message

) {
}