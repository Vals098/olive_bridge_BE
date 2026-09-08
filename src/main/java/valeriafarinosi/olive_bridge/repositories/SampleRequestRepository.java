package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.SampleRequest;
import valeriafarinosi.olive_bridge.entities.User;

import java.util.List;
import java.util.UUID;

public interface SampleRequestRepository
        extends JpaRepository<SampleRequest, UUID> {

    List<SampleRequest> findByUser(User user);
}