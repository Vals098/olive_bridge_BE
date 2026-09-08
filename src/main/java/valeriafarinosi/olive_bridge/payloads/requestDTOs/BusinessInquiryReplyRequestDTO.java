package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotBlank;

public record BusinessInquiryReplyRequestDTO(

        @NotBlank(message = "Reply message cannot be empty.")
        String message

) {
}