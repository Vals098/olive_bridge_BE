package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderItemRequestDTO(
        @NotNull(message = "Product variant is required.")
        UUID productVariantId,

        @Positive(message = "Quantity must be greater than zero.")
        int quantity
) {
}