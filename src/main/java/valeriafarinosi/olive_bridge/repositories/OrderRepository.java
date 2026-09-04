package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}