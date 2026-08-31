package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Getter
@ToString
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID orderItemId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    public OrderItem(
            int quantity,
            BigDecimal unitPrice,
            Order order,
            ProductVariant productVariant
    ) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
        this.productVariant = productVariant;
    }
}
