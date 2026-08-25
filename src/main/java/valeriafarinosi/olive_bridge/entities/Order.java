package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@ToString
public class Order {

    @Id
    @GeneratedValue
    private UUID productId;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //    customer may change address after making an order.
//    old address data needed in the order history
    @Column(nullable = false)
    private String shippingRecipientName;

    @Column(nullable = false)
    private String shippingPostalCode;

    @Column(nullable = false)
    private String shippingPrefecture;

    @Column(nullable = false)
    private String shippingCity;

    @Column(nullable = false)
    private String shippingArea;

    @Column(nullable = false)
    private String shippingStreet;

    private String shippingBuilding;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
