package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private BigDecimal acidity;

    @Column(nullable = false)
    private BigDecimal peroxideValue;

    @Column(nullable = false)
    private LocalDate harvestDate;

    @Column(nullable = false)
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
