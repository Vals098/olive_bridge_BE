package valeriafarinosi.olive_bridge.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.entities.SampleRequest;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.SampleRequestStatus;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.repositories.ProductRepository;
import valeriafarinosi.olive_bridge.repositories.SampleRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SampleRequestService {

    private final SampleRequestRepository sampleRequestRepository;
    private final ProductRepository productRepository;

    public SampleRequestService(
            SampleRequestRepository sampleRequestRepository,
            ProductRepository productRepository
    ) {
        this.sampleRequestRepository = sampleRequestRepository;
        this.productRepository = productRepository;
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    private void checkBusinessAccount(User user) {
        if (user.getAccountType() != AccountType.BUSINESS) {
            throw new BadRequestException(
                    "This feature is reserved for business accounts."
            );
        }
    }

    public List<SampleRequestResponseDTO> getMySampleRequests() {
        User currentUser = getCurrentUser();

        return sampleRequestRepository.findByUser(currentUser)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<SampleRequestResponseDTO> getAllSampleRequests() {
        return sampleRequestRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SampleRequestResponseDTO createSampleRequest(
            SampleRequestRequestDTO body
    ) {
        User currentUser = getCurrentUser();

        checkBusinessAccount(currentUser);

        Product product = productRepository.findById(body.productId())
                .orElseThrow(() ->
                        new NotFoundException("Product not found.")
                );

        SampleRequest sampleRequest = new SampleRequest(
                currentUser,
                product,
                body.message(),
                SampleRequestStatus.PENDING,
                LocalDateTime.now()
        );

        SampleRequest savedSampleRequest =
                sampleRequestRepository.save(sampleRequest);

        return toResponseDTO(savedSampleRequest);
    }

    private SampleRequestResponseDTO toResponseDTO(
            SampleRequest sampleRequest
    ) {
        return new SampleRequestResponseDTO(
                sampleRequest.getSampleRequestId(),
                sampleRequest.getProduct().getProductId(),
                sampleRequest.getMessage(),
                sampleRequest.getStatus(),
                sampleRequest.getCreatedAt()
        );
    }
}