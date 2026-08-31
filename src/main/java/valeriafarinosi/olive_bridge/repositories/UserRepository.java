package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}