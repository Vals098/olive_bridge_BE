package valeriafarinosi.olive_bridge.payloads.responseDTOs;

import valeriafarinosi.olive_bridge.enums.BusinessInquiryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BusinessInquiryResponseDTO(
        UUID businessInquiryId,
        String subject,
        String message,
        BusinessInquiryStatus status,
        LocalDateTime createdAt
) {
}