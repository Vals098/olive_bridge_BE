package valeriafarinosi.olive_bridge.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

import java.util.UUID;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@ToString
public class Product {

    @Id
    @GeneratedValue
    private UUID productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "product_img")
    private String image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne
    @JoinColumn(
            name = "technical_information_id",
            nullable = false,
            unique = true
    )
    private TechnicalInformation technicalInformation;

    public Product(String name, String description, String image, ActiveStatus status, Category category, TechnicalInformation technicalInformation) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
        this.category = category;
        this.technicalInformation = technicalInformation;
    }


}
