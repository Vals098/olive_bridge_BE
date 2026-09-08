package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import valeriafarinosi.olive_bridge.enums.SampleRequestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record SampleRequestResponseDTO(
        UUID sampleRequestId,
        UUID productId,
        String message,
        SampleRequestStatus status,
        LocalDateTime createdAt,

        // SHIPPING ADDRESS
        String recipientName,
        String postalCode,
        String prefecture,
        String city,
        String area,
        String street,
        String building
) {
}