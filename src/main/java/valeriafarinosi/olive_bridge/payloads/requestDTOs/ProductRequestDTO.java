package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

import java.util.UUID;

public record ProductRequestDTO(

        @NotBlank(message = "Product name is required.")
        String name,

        @NotBlank(message = "Product description is required.")
        String description,

        String image,

        @NotNull(message = "Product status is required.")
        ActiveStatus status,

        @NotNull(message = "Category id is required.")
        UUID categoryId,

        @NotNull(message = "Technical information id is required.")
        UUID technicalInformationId

) {
}