package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

public record CategoryRequestDTO(
        @NotBlank(message = "Category name is required.")
        String name,

        @NotBlank(message = "Category description is required.")
        String description,

        @NotNull(message = "Category status is required.")
        ActiveStatus status
) {
}