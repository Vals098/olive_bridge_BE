package valeriafarinosi.olive_bridge.payloads.requestDTOs;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TechnicalInformationRequestDTO(

        @NotNull(message = "Acidity is required.")
        BigDecimal acidity,

        @NotNull(message = "Peroxide value is required.")
        BigDecimal peroxideValue,

        @NotNull(message = "Harvest date is required.")
        LocalDate harvestDate,

        @NotNull(message = "Best before date is required.")
        LocalDate bestBeforeDate
) {
}