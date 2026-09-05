package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}