package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.OrderStatus;
import valeriafarinosi.olive_bridge.enums.PaymentMethod;
import valeriafarinosi.olive_bridge.enums.PaymentStatus;

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
    private UUID orderId;

    // Guest customers are not users but can place orders.
    // User must be nullable.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // Will be used if Stripe is implemented in the future.
    // Nullable for now because we are not using Stripe yet.
    private String stripePaymentId;

    // Customer may change address after making an order.
    // Old address data is needed in the order history.
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

    @Column(nullable = false)
    private String billingPostalCode;

    @Column(nullable = false)
    private String billingPrefecture;

    @Column(nullable = false)
    private String billingCity;

    @Column(nullable = false)
    private String billingArea;

    @Column(nullable = false)
    private String billingStreet;

    private String billingBuilding;

    public Order(
            User user,
            String customerEmail,
            LocalDateTime orderDate,
            BigDecimal total,
            OrderStatus status,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus,

            // SHIPPING
            String shippingRecipientName,
            String shippingPostalCode,
            String shippingPrefecture,
            String shippingCity,
            String shippingArea,
            String shippingStreet,
            String shippingBuilding,

            // BILLING
            String billingPostalCode,
            String billingPrefecture,
            String billingCity,
            String billingArea,
            String billingStreet,
            String billingBuilding
    ) {
        this.user = user;
        this.customerEmail = customerEmail;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;

        this.shippingRecipientName = shippingRecipientName;
        this.shippingPostalCode = shippingPostalCode;
        this.shippingPrefecture = shippingPrefecture;
        this.shippingCity = shippingCity;
        this.shippingArea = shippingArea;
        this.shippingStreet = shippingStreet;
        this.shippingBuilding = shippingBuilding;

        this.billingPostalCode = billingPostalCode;
        this.billingPrefecture = billingPrefecture;
        this.billingCity = billingCity;
        this.billingArea = billingArea;
        this.billingStreet = billingStreet;
        this.billingBuilding = billingBuilding;
    }
}