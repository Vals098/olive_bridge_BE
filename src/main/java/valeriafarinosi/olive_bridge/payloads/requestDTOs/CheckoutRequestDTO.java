package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CheckoutRequestDTO(

        @NotBlank(message = "Customer name is required.")
        String customerName,

        @NotBlank(message = "Customer email is required.")
        @Email(message = "Customer email must be valid.")
        String customerEmail,

        @NotBlank(message = "Postal code is required.")
        String shippingPostalCode,

        @NotBlank(message = "Prefecture is required.")
        String shippingPrefecture,

        @NotBlank(message = "City is required.")
        String shippingCity,

        @NotBlank(message = "Area is required.")
        String shippingArea,

        @NotBlank(message = "Street is required.")
        String shippingStreet,

        String shippingBuilding,

        @NotEmpty(message = "The order must contain at least one item.")
        @Valid
        List<OrderItemRequestDTO> items
) {
}