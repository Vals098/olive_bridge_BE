package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "favourites",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "product_id"})
        }
)
@NoArgsConstructor
@Getter
@ToString
public class Favourite {

    @Id
    @GeneratedValue
    private UUID favouriteId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Favourite(User user, Product product, LocalDateTime createdAt) {
        this.user = user;
        this.product = product;
        this.createdAt = createdAt;
    }
}
