package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotNull;
import valeriafarinosi.olive_bridge.enums.SampleRequestStatus;

public record SampleRequestStatusRequestDTO(

        @NotNull(message = "Status cannot be null.")
        SampleRequestStatus status

) {
}