package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.SampleRequestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sample_requests")
@NoArgsConstructor
@Getter
@ToString
public class SampleRequest {

    @Id
    @GeneratedValue
    private UUID sampleRequestId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SampleRequestStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // SHIPPING ADDRESS
    @Column(nullable = false)
    private String recipientName;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String prefecture;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String street;

    private String building;

    public SampleRequest(
            User user,
            Product product,
            String message,
            SampleRequestStatus status,
            LocalDateTime createdAt,
            String recipientName,
            String postalCode,
            String prefecture,
            String city,
            String area,
            String street,
            String building
    ) {

        this.user = user;
        this.product = product;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.recipientName = recipientName;
        this.postalCode = postalCode;
        this.prefecture = prefecture;
        this.city = city;
        this.area = area;
        this.street = street;
        this.building = building;
    }

    public void setStatus(SampleRequestStatus status) {
        this.status = status;
    }
}

