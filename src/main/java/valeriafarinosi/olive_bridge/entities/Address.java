package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@ToString
public class Address {

    @Id
    @GeneratedValue
    private UUID addressId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String label;

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

    public Address(
            User user,
            String label,
            String recipientName,
            String postalCode,
            String prefecture,
            String city,
            String area,
            String street,
            String building
    ) {
        this.user = user;
        this.label = label;
        this.recipientName = recipientName;
        this.postalCode = postalCode;
        this.prefecture = prefecture;
        this.city = city;
        this.area = area;
        this.street = street;
        this.building = building;
    }
}
