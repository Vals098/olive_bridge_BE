package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.TechnicalInformation;

import java.util.UUID;

public interface TechnicalInformationRepository extends JpaRepository<TechnicalInformation, UUID> {
}