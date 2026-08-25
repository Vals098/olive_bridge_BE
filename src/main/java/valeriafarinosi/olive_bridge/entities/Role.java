package valeriafarinosi.olive_bridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@ToString
public class Role {

    @Id
    @GeneratedValue
    private UUID roleId;

    @Column(nullable = false)
    private String name;

}
