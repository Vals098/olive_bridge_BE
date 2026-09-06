package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BusinessInquiryRequestDTO(

        @NotBlank(message = "Subject is required.")
        @Size(max = 100, message = "Subject cannot exceed 100 characters.")
        String subject,

        @NotBlank(message = "Message is required.")
        String message
) {
}