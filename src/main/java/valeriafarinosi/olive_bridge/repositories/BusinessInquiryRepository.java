package valeriafarinosi.olive_bridge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valeriafarinosi.olive_bridge.entities.BusinessInquiry;
import valeriafarinosi.olive_bridge.entities.User;

import java.util.List;
import java.util.UUID;

public interface BusinessInquiryRepository
        extends JpaRepository<BusinessInquiry, UUID> {

    List<BusinessInquiry> findByUser(User user);
}