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

        // SHIPPING ADDRESS
        @NotBlank(message = "Shipping postal code is required.")
        String shippingPostalCode,

        @NotBlank(message = "Shipping prefecture is required.")
        String shippingPrefecture,

        @NotBlank(message = "Shipping city is required.")
        String shippingCity,

        @NotBlank(message = "Shipping area is required.")
        String shippingArea,

        @NotBlank(message = "Shipping street is required.")
        String shippingStreet,

        String shippingBuilding,

        // BILLING ADDRESS
        @NotBlank(message = "Billing postal code is required.")
        String billingPostalCode,

        @NotBlank(message = "Billing prefecture is required.")
        String billingPrefecture,

        @NotBlank(message = "Billing city is required.")
        String billingCity,

        @NotBlank(message = "Billing area is required.")
        String billingArea,

        @NotBlank(message = "Billing street is required.")
        String billingStreet,

        String billingBuilding,

        @NotEmpty(message = "The order must contain at least one item.")
        @Valid
        List<OrderItemRequestDTO> items
) {
}