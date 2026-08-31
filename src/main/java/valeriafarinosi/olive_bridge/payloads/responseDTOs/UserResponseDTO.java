package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

import java.util.UUID;

public record UserResponseDTO(
        UUID userId,
        String name,
        String surname,
        String email,
        String role,
        AccountType accountType,
        ActiveStatus status
) {
}