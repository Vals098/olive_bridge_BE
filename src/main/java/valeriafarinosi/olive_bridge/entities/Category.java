package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;

import java.util.UUID;

@Entity
@Table(name = "product_categories")
@NoArgsConstructor
@Getter
@ToString
public class Category {

    @Id
    @GeneratedValue
    private UUID categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    public Category(String name, String description, ActiveStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
