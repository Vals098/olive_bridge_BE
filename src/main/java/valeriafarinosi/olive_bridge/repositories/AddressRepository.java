package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.Address;
import valeriafarinosi.olive_bridge.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByUser(User user);

    Optional<Address> findByAddressIdAndUser(UUID addressId, User user);

}