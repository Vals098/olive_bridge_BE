package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.BusinessInquiryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "business_inquiries")
@NoArgsConstructor
@Getter
@ToString
public class BusinessInquiry {

    @Id
    @GeneratedValue
    private UUID businessInquiryId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessInquiryStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public BusinessInquiry(
            User user,
            String subject,
            String message,
            BusinessInquiryStatus status,
            LocalDateTime createdAt
    ) {
        this.user = user;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }
}
