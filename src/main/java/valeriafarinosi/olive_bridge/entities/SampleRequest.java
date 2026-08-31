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

    public SampleRequest(
            User user,
            Product product,
            String message,
            SampleRequestStatus status,
            LocalDateTime createdAt
    ) {
        this.user = user;
        this.product = product;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }
}
