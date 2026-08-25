package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_variants")
@NoArgsConstructor
@Getter
@ToString
public class ProductVariant {

    @Id
    @GeneratedValue
    private UUID productVariantId;

    @Column(nullable = false)
    private String format;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "variant_img")
    private String image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductVariant(
            String format,
            BigDecimal price,
            String image,
            ActiveStatus status,
            Product product
    ) {
        this.format = format;
        this.price = price;
        this.image = image;
        this.status = status;
        this.product = product;
    }
}
