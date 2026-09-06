package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.Favourite;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavouriteRepository extends JpaRepository<Favourite, UUID> {

    List<Favourite> findByUser(User user);

    Optional<Favourite> findByUserAndProduct(User user, Product product);
}