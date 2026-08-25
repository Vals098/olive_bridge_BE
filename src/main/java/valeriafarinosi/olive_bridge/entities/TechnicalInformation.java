package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "technical_information")
@NoArgsConstructor
@Getter
@ToString
public class TechnicalInformation {

    @Id
    @GeneratedValue
    private UUID technicalInformationId;

    private BigDecimal acidity;

    private BigDecimal peroxideValue;

    private LocalDate harvestDate;

    private LocalDate bestBeforeDate;

    public TechnicalInformation(
            BigDecimal acidity,
            BigDecimal peroxideValue,
            LocalDate harvestDate,
            LocalDate bestBeforeDate
    ) {
        this.acidity = acidity;
        this.peroxideValue = peroxideValue;
        this.harvestDate = harvestDate;
        this.bestBeforeDate = bestBeforeDate;
    }

}
