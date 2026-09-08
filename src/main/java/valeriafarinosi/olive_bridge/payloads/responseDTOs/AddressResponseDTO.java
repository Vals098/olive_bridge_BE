package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import java.util.UUID;

public record AddressResponseDTO(
        UUID addressId,
        String label,
        String recipientName,
        String postalCode,
        String prefecture,
        String city,
        String area,
        String street,
        String building
) {
}